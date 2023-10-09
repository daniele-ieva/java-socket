package com.networking;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Network {
    private BufferedReader in;
    private PrintWriter out;

    private String address;

    private Integer port;
    private Socket client;

    public void send(Message msg) {
        System.out.println(msg);
        if (msg == null) {
            msg = new Message(Message.Status.ERROR, "Empty Message");
        }
        out.println(msg);
    }
    public Message recieve() {
        String msg = null;
        try {
             msg = in.readLine();
        } catch (IOException e) { throw new RuntimeException(e); }
        return Message.parse(msg);
    }
    public Client(String address, Integer port) {
        this.address = address;
        this.port = port;
    }
    public void connect() {
        try {
            this.client = new Socket(this.address, this.port);
            System.out.println("Connection Established");
            this.in = new BufferedReader(
                    new InputStreamReader( client.getInputStream() )
            );
            this.out = new PrintWriter(this.client.getOutputStream(), true);
        } catch (IOException e) { throw new RuntimeException(e); }

    }
    public void sendConsole() {
        Scanner s = new Scanner(System.in);
        String msg = s.next();
        this.send(new Message(Message.Status.OK, msg));
    }
    public void close() {
        try {
            this.client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

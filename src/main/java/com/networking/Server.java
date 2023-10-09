package com.networking;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server implements Network {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private final String address;
    private final Integer port;
    private ServerSocket server = null;
    private Socket client = null;
    private final Message stop = new Message(Message.Status.OK, ".");

    public void send(Message msg) {
        out.println(msg);
    }
    public Message recieve() {
        String msg;
        try {
            msg = in.readLine();
        } catch (IOException e) { throw new RuntimeException(e); }
        return Message.parse(msg);
    }
    public void sendConsole() {
        Scanner s = new Scanner(System.in);
        String msg = s.next();
        this.send(new Message(Message.Status.OK, msg));
    }
    public Server(Integer port) {
        this.port = port;
        try {
            this.address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) { throw new RuntimeException(e); }
    }
    public void start() {
        try {
            this.server = new ServerSocket(this.port);
            System.out.println("Started the server at: " + this);
            this.client = server.accept();
            System.out.println("Connection Established");
            this.in = new BufferedReader(
                    new InputStreamReader( this.client.getInputStream())
            );
            this.out = new PrintWriter(this.client.getOutputStream(), true);
        } catch (IOException e) { throw new RuntimeException(e);}

    }
    public void close() {
        try {
            this.client.close();
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void mainloop() {
        if (this.server == null) {
            this.start();
        }

        boolean stop = false;
        Message msg;
        while (!stop) {
            msg = this.recieve();
            System.out.println(msg);
            if (msg.equals(this.stop)) {
                stop = true;
                this.send(this.stop);
            }
            else {
                this.sendConsole();
            }
        }
        this.close();
    }

    @Override
    public String toString() {
        return this.address + ":" + this.port.toString();
    }

}

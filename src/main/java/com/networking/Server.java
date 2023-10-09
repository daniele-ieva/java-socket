package com.networking;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server implements Network {
    private BufferedReader in = null;
    private PrintWriter out = null;
    private final String address;
    private final Integer port;
    private ServerSocket server = null;
    private Socket client = null;

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
    @Override
    public String toString() {
        return this.address + ":" + this.port.toString();
    }
    public void close() {
        try {
            this.client.close();
            this.server.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.networking;

import java.io.*;
import java.net.Inet4Address;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Server {
    private Scanner in = null;
    private PrintWriter out = null;
    private final String address;
    private final Integer port;
    private ServerSocket server = null;
    private Socket client = null;

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
            this.in = new Scanner(this.client.getInputStream());
            this.out = new PrintWriter(this.client.getOutputStream(), true);
        } catch (IOException e) { this.close(); throw new RuntimeException(e);}

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

        Mutex running = new Mutex(true);
        ReaderThread reader = new ReaderThread(this.in, running);
        WriterThread writer = new WriterThread(this.out, running);
        reader.start();
        writer.start();
        while (running.status());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) { throw new RuntimeException(e); }
        this.close();
    }

    @Override
    public String toString() {
        return this.address + ":" + this.port.toString();
    }

}

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

    /**
     * A 1 to 1 server for a single client
     * @param port the port to bind to the server socket
     * @see ServerSocket
     **/
    public Server(Integer port) {
        this.port = port;
        try {
            this.address = Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) { throw new RuntimeException(e); }
    }
    /**
     * Start the server and accept a connection from a client
     **/
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

    /**
     * Launch the main loop for the server that spawns two new threads for IO and to allow sending and receiving ù
     * multiple messages asynchronously
     **/
    public void mainloop() {
        if (this.server == null) {
            this.start();
        }

        IOUtils.syncIO(this.in, this.out);
        this.close();
    }

    /**
     * Override of Object.toString().
     * returns the address of the server as an ipv4 in dot decimal notation
     **/
    @Override
    public String toString() {
        return this.address + ":" + this.port.toString();
    }

}

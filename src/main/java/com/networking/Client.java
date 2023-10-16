package com.networking;

import java.io.*;
import java.net.Socket;

public class Client {
    private BufferedReader in;
    private PrintWriter out;
    private final String address;
    private final Integer port;
    private Socket client;

    public Client(String address, Integer port) {
        this.address = address;
        this.port = port;
    }

    public void mainloop() {
        if (this.client == null) {
            this.connect();
        }
        IOUtils.syncIO(this.in, this.out);
        this.close();
    }

    public void connect() {
        try {
            this.client = new Socket(this.address, this.port);
            System.out.println("Connection Established");
            this.in = new BufferedReader(
                    new InputStreamReader(this.client.getInputStream())
            );
            this.out = new PrintWriter(this.client.getOutputStream(), true);
        } catch (IOException e) { throw new RuntimeException(e); }

    }

    public void close() {
        try {
            this.client.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

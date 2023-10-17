package com.networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.nio.Buffer;
import java.util.Scanner;

public class ReaderThread extends Thread {
    private final BufferedReader in;
    private final Mutex running;
    /**
     * A class generating a thread to read from a given Scanner asynchronously
     * @see Thread
     * @param in a scanner for a given InputStream
     * @see Scanner
     * @param running a mutex containing the status of the application, in order to stop when required
     * @see Mutex
     **/
    public ReaderThread(BufferedReader in, Mutex running) {
        this.in = in;
        this.running = running;
    }
    public void run() {
        this.mainloop();
    }
    public void mainloop() {
        Message reader;
        while (this.running.status()) {
            try {
                reader = Message.parse(in.readLine());
            }
            catch (IOException e) { break; }
            System.out.println(reader);
            if (reader.equals(Message.stop())) {
                this.running.swap();
                System.out.println(this.running.status());
            }
            if (reader.status().equals(Message.Status.ERROR)) {
                this.running.swap();
            }
        }
    }
}

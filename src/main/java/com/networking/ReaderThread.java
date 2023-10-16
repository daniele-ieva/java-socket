package com.networking;

import java.io.IOException;
import java.util.Scanner;

public class ReaderThread extends Thread {
    private final Scanner in;
    private final Mutex running;
    public ReaderThread(Scanner in, Mutex running) {
        this.in = in;
        this.running = running;
    }
    public void run() {
        this.mainloop();
    }
    public void mainloop() {
        Message reader;
        while (this.running.status()) {


            if (in.hasNext()) {
                reader = Message.parse(in.nextLine());
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
}

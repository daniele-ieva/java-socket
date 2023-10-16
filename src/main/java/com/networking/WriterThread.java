package com.networking;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class WriterThread extends Thread {

    private final PrintWriter out;
    private final Mutex running;
    private final Scanner s = new Scanner(System.in);
    public WriterThread(PrintWriter out, Mutex running) {
        this.out = out;
        this.running = running;
    }

    public void send(Message msg) {
        out.println(msg);
    }

    public void mainloop() {
        String msg_str;
        Message msg;
        while (this.running.status()) {
            msg_str = s.nextLine();
            if (this.running.status()) {
                msg = new Message(Message.Status.OK, msg_str);
                this.send(msg);
                if (msg.equals(Message.stop())) {
                    this.running.swap();
                }
            }
        }
    }
    @Override
    public void run() {
        mainloop();
    }
}

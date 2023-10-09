package com.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface Network {

    BufferedReader in = null;
    BufferedWriter out = null;
    String address = null;
    Integer port = null;
    final Message stop = new Message(Message.Status.OK, ".");

    void send(Message msg);
    Message recieve();
    void sendConsole();
    void mainloop();
}

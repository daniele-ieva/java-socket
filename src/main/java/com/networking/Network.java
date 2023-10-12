package com.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface Network {
    // Attributes
    BufferedReader in = null;
    BufferedWriter out = null;
    String address = null;
    Integer port = null;
    // Methods
    void send(Message msg);
    Message recieve();
    void sendConsole();
    void mainloop();
}

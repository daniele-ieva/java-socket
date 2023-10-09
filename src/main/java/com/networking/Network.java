package com.networking;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface Network {

    BufferedReader in = null;
    BufferedWriter out = null;
    String address = null;
    Integer port = null;

    void send(Message msg);
    Message recieve();
}

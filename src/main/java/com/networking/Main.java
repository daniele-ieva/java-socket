package com.networking;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String sel;
        System.out.print("Insert mode of operation (client/server): ");
        sel = s.next();
        if (sel.equals("client")) {
            System.out.print("Insert server address: ");
            sel = s.next();
            String[] sep;
            sep = sel.split(":");
            Client client = new Client(sep[0], Integer.parseInt(sep[1]));
            client.connect();
            client.sendConsole();
            client.close();
            return;
        }
        if (sel.equals("server")) {
            System.out.print("Insert server port: ");
            sel = s.next();
            Server server = new Server(Integer.parseInt(sel));
            server.start();
            System.out.println(server.recieve());
            server.close();
            return;
        }
        System.out.println("Please select a valid mode!");
        main(args);
    }
}
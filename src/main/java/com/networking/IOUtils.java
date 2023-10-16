package com.networking;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class IOUtils {
    /**
     * Allows for synchronous IO operations on the given stream
     * @param in a Scanner from which to read data
     * @param out a PrintWriter where to write console input to
     **/
    public static void syncIO(Scanner in, PrintWriter out) {
        Mutex running = new Mutex(true);
        ReaderThread reader = new ReaderThread(in, running);
        WriterThread writer = new WriterThread(out, running);
        reader.start();
        writer.start();
        while (running.status());
        reader.interrupt();
        writer.interrupt();
    }
}

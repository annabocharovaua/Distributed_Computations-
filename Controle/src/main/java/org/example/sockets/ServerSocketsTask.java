package org.example.sockets;

import org.example.model.Abiturient;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Callback{
    public boolean shouldEnd = false;
}


public class ServerSocketsTask{
    private static ServerSocket server;

    private static int port = 8888;

    public static Callback c = new Callback();

    public static void main(String args[]) throws IOException, ClassNotFoundException{
        server = new ServerSocket(port);
        List<Abiturient> abiturients = new ArrayList<>() {
            {
                add(new Abiturient("1", "Anna", "Bocharova",
                    "Shostka", "0998334585", new int[]{10, 10}));
                add(new Abiturient("2", "Ivan", "Bega",
                        "Kyiv", "0504235886", new int[]{9, 10}));
                add(new Abiturient("3", "Katya", "Moloday",
                        "Kharkiv", "0665478954", new int[]{10, 10}));
            }
        };

        while(!c.shouldEnd){
            System.out.println("Waiting for the client request");
            Socket socket = server.accept();
            AbiturientIterator iterator = new AbiturientIterator(socket, c, abiturients);
            iterator.run();
        }
        System.out.println("Shutting down Socket server");
        server.close();
    }
}
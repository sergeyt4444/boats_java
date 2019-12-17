package socket_manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import server.*;
import socket_listener.*;

public class socket_manager extends Thread implements abstract_socket_manager {

    public ArrayList<DataOutputStream> allOutputs;

    public void run() {
        allOutputs = new ArrayList<>();
        ServerSocket serverSocket = null;
        Socket socket = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        Gson json = new GsonBuilder().setPrettyPrinting().create();

        try {
            serverSocket = new ServerSocket(4443);

            while (true) {
                socket = serverSocket.accept();
                dis = new DataInputStream(socket.getInputStream());
                dos = new DataOutputStream(socket.getOutputStream());
                String map_line = json.toJson(server.fm.m);

                String boats_line = json.toJson(server.fm.boats);

                dos.writeUTF(map_line);
                dos.writeUTF(boats_line);
                if (dos != null && allOutputs != null)
                    allOutputs.add(dos);

                new socket_listener(socket, dis, dos).start();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

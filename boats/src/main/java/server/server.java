package server;

import algorithm.algorithm;
import boat_table.boat_table;
import boats.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import full_map.full_map;
import map.*;
import socket_listener.*;
import socket_manager.*;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class server {

    static public full_map fm;

    static public List<socket_manager> sockets;

    public static void SendNewMap(full_map fm) {
        for (boat_table b : fm.boats) {
            System.out.println(b);
        }
        fm.m.print();
    }

    public static void main(String[] args) throws InterruptedException {
        map testmap = new map(4,4, null);
        model m = BModel.build();
        List<boat_table> boats2 = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats2);
        fm = new full_map(testmap, boats2);
        algorithm alg = new algorithm();
        for (boat_table b_start : fm.boats) {
            alg.findPath(fm, b_start);
        }

        Gson json = new GsonBuilder().setPrettyPrinting().create();
        socket_manager sm = new socket_manager();
        sm.start();
//        CreateListeners();
        while(true) {
            TimeUnit.SECONDS.sleep(5);
            for (boat_table b : fm.boats) {
                alg.moveBoat(fm, b);
            }
            for (boat_table b : fm.boats) {
                alg.findPath(fm, b);
            }
            String blist;
            blist = json.toJson(fm.boats);
            if (sm != null && sm.allOutputs != null) {
                for (DataOutputStream dos : sm.allOutputs) {
                    if (dos != null) {
                        try {
                            dos.writeUTF(blist);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
            SendNewMap(fm);
        }

    }

}

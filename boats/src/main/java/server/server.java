package server;

import algorithm.algorithm;
import boat_table.boat_table;
import boats.*;
import full_map.full_map;
import map.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class server {

    public static void SendNewMap(full_map fm) {
        for (boat_table b : fm.boats) {
            System.out.println(b);
        }
        fm.m.print();
    }

    public static void main(String[] args) throws InterruptedException {
        map testmap = new map(4,4, null);
        model m = BModel.build();
        List<boat_table> boats = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats);
        full_map fm = new full_map(testmap, boats);
        algorithm alg = new algorithm();
//        CreateListeners();
        while(true) {
            TimeUnit.SECONDS.sleep(5);
            for (boat_table b : fm.boats) {
                alg.moveBoat(fm, b);
            }
            SendNewMap(fm);
        }

    }

}

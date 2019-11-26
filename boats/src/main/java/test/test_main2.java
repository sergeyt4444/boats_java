package test;

import boat_table.boat_table;
import boats.*;
import full_map.full_map;
import map.map;
import algorithm.*;

import java.util.List;

public class test_main2 {
    public static void main(String[] args) {
        map testmap = new map(4,4, null);
        model m = BModel.build();
        List<boat_table> boats = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats);
        full_map fm = new full_map(testmap, boats);
        algorithm alg = new algorithm();
        alg.findPath(fm, fm.boats.get(1));


    }
}

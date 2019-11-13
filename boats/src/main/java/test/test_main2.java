package test;

import boats.*;

import java.util.List;

public class test_main2 {
    public static void main(String[] args) {
        map testmap = new map(4,4, null);
        model m = BModel.build();
        List<boat_table> boats = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats);
        full_map fm = new full_map(testmap, boats);
        path p = new path();
        p = fm.getPath(fm.boats.get(1));

    }
}

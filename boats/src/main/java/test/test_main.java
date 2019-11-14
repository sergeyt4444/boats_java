/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import boat_table.boat_table;
import boats.*;
import map.map;

import java.util.List;

/**
 *
 * @author serge
 */
public class test_main {
    
    public static void main(String[] args) {
        map testmap = new map(4,4, null);
        model m = BModel.build();
        List<boat_table> boats = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats);
        
        for (boat_table b : boats) {
            System.out.println(b);
        }
        
        testmap.print();
        
//        testmap.map_list.get(3).setIsWater(1);
//        m.updateMap(testmap.map_list.get(3));
//        for (boat_table b : boats) {
//            if (b.getName().equals("boat2")) {
//                b.setY_cur(b.getY_cur()-1);
//                m.updateBoat(b);
//            }
//        }
        testmap.placeBoats(boats);
        for (boat_table b : boats) {
            System.out.println(b);
        }
        
        testmap.print();
        
        boat_table new_boat = new boat_table("boat3", 3, 3, 1,3);
        boats.add(new_boat);
        System.out.println(new_boat);
        m.insertBoat(new_boat);
        boats = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats);
        
        for (boat_table b : boats) {
            System.out.println(b);
        }        
        testmap.print();
        
        m.deleteBoat(new_boat);
        boats = m.getBoats();
        testmap.map_list = m.getMap();
        testmap.placeBoats(boats);
        
        for (boat_table b : boats) {
            System.out.println(b);
        }
        testmap.print();
//        m.clearMap();
//        map_part mp = new map_part(4,0);
//        m.insertMap(mp);
//        boats = m.getBoats();
//        testmap.map_list = m.getMap();
//        testmap.placeBoats(boats);
//        
//        for (boat_table b : boats) {
//            System.out.println(b);
//        }        
//        testmap.print();
    }
    
}

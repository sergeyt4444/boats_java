/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import boats.*;
import java.util.List;

/**
 *
 * @author serge
 */
public class test_main {
    
    public static void main(String[] args) {
        map testmap = new map(4,4, null);
        infr inf = new infr();
        List<boat_table> boats = inf.getBoats();
        testmap.map_list = inf.getMap();
        
        for (boat_table b : boats) {
            System.out.println(b);
        }
        
        testmap.print();
        
    }
    
}

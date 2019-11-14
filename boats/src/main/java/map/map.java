/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package map;

import boat_table.boat_table;
import map_part.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class map implements abstract_map{
    
    public int width;
    public int height;
    public ArrayList<map_part> map_list;
    
    public map() {
        width = height = 16;
        for (int i = 0; i < 256; i++)
        {
            map_part m = new map_part(i%width, i/width);
            m.setX(i%width);
            m.setY(i/width);
            map_list.add(m);
        }
    }
    
    public map(int w, int h, ArrayList<map_part> arr) {
        width = w;
        height = h;
        map_list = arr;
    }
    
    public void placeBoats(List<boat_table> boats) {
        for (map_part mp : map_list) {
            mp.setCur_boat("");
        }
        for (boat_table boat : boats) {
            map_list.get(boat.getX_cur() + width * boat.getY_cur()).setCur_boat(boat.getName());
        }
    }
    
    public void print() {
        if (map_list != null) {
            System.out.println("Width: " + width + ", height: " + height);
            for (map_part mp : map_list) {
                System.out.println(mp);
            }
        }
    }
    
}

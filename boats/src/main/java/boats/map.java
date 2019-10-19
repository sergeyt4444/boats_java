/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.util.ArrayList;

/**
 *
 * @author serge
 */
public class map {
    
    public int width;
    public int height;
    public ArrayList<map_part> map_list;
    
    public map() {
        width = height = 16;
        for (int i = 0; i < 256; i++)
        {
            map_part m = new map_part(i%width, i/width);
            map_list.add(m);
        }
    }
    
    public map(int w, int h, ArrayList<map_part> arr) {
        width = w;
        height = h;
        map_list = arr;
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

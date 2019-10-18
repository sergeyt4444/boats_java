/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats_server;

import java.util.List;

/**
 *
 * @author serge
 */
public class map {
    
    public int width;
    public int height;
    List<map_part> map_list;
    
    public map() {
        width = height = 16;
        for (int i = 0; i < 256; i++)
        {
            map_part m = new map_part(i%width, i/width);
            map_list.add(i,m);
        }
    }
    
}

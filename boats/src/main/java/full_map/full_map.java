/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package full_map;

import boat_table.boat_table;
import boats.BModel;
import boats.Pair;
import boats.model;
import boats.path;
import map.map;
import map_part.map_part;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author serge
 */
public class full_map implements abstract_full_map{
    public map m;
    public List<boat_table> boats;
    public model mod;
    
    public full_map () {
        mod = BModel.build();
    }

    public full_map (map m1, List<boat_table> b1) {
        mod = BModel.build();
        m = m1;
        boats = b1;
    }
    
    public List<map_part> getNear(map_part mp) {
        List<map_part> tmp = new ArrayList<>();
        int mp_x = mp.getX();
        int mp_y = mp.getY();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <=1; j++) {
                if ((i != 0 || j != 0) && mp_x + j > -1 && mp_y + i > -1 && mp_x + j < m.width && mp_y + i < m.height)
                    tmp.add(m.map_list.get(mp_x + j + (mp_y + i)*m.width));
            }
        return tmp;
    }
    
    public List<Pair> getNearPairs(Pair c) {
        List<Pair> tmp = new ArrayList<>();
        int x = c.x;
        int y = c.y;
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <=1; j++) {
                if ((i != 0 || j != 0) && x + j > -1 && y + i > -1 && x + j < m.width && y + i < m.height) {
                    Pair p = new Pair(x + j, y + i);
                    tmp.add(p);
                }
            }
        return tmp;
        
    }
    
    public int[][] mapToMatrix() {
        int[][] matrix = new int[m.height][m.width];
        for (int i = 0; i < m.height; i++)
            for (int j = 0; j < m.width; j++) {
                if (m.map_list.get(j + i*m.width).getIsWater() == 1)
                    matrix[i][j] = 0;
                else matrix [i][j] = -1;
            }
        return matrix;
    }
    
    public int moveBoat(boat_table boat, map_part mp) {
        
        if(boat == null || mp == null)
            return -1;
        if (!(mp.getCur_boat().equals("")) || mp.getIsWater() == 0)
            return -1;
        int boat_x = boat.getX_cur();
        int boat_y = boat.getY_cur();
        m.map_list.get(boat_x + m.width*boat_y).setCur_boat("");
        boat.setX_cur(mp.getX());
        boat.setY_cur(mp.getY());
        mp.setCur_boat(boat.getName());
        mod.updateBoat(boat);
        return 0;
    }
    
    public int moveBoatby1(boat_table boat, map_part mp) {
        if(boat == null || mp == null)
            return -1;
        int delta_x = mp.getX() - boat.getX_cur();
        int delta_y = mp.getY() - boat.getY_cur();
        if (delta_x > 1 || delta_x < -1 || delta_y > 1 || delta_y < -1)
            return -1;
        return moveBoat(boat, mp);
        }
    
    public int moveBoatby1(boat_table boat, int delta_x, int delta_y) {
        if(boat == null)
            return -1;
        if (delta_x < -1 || delta_x >1 || delta_y > 1 || delta_y < -1)
            return -1;
        int boat_x = boat.getX_cur();
        int boat_y = boat.getY_cur();
        if (boat_x + delta_x < 0 || boat_x + delta_x >= m.width || boat_y + delta_y < 0 || boat_y + delta_y >= m.height) 
            return -1;
        map_part dest = m.map_list.get(boat_x + delta_x + m.width*(boat_y+delta_y));
        if (!(dest.getCur_boat().equals("")) || dest.getIsWater() == 0)
            return -1;
        m.map_list.get(boat_x + m.width*boat_y).setCur_boat("");
        boat.setX_cur(boat_x + delta_x);
        boat.setY_cur(boat_y + delta_y);
        dest.setCur_boat(boat.getName());
        mod.updateBoat(boat);
        return 0;
    }
    
    public int checkMap(map new_map) {
        return 0;
    }
    
    public int changeMap(map new_map) {
        if (checkMap(new_map) == -1)
            return -1;
        mod.clearMap();
        m = new_map;
        for (int i = 0; i < new_map.height * new_map.width; i++) {
            map_part tmp = new_map.map_list.get(i);
            mod.insertMap(tmp);
        }
        return 0;
    }

    public void deleteBoat(boat_table boat) {
        mod.deleteBoat(boat);
        boats = mod.getBoats();
        m.map_list = mod.getMap();
    }
    
}



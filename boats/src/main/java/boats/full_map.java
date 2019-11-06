/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author serge
 */
public class full_map {
    public map m;
    public List<boat_table> boats;
    public model mod;
    
    public full_map () {
        mod = BModel.build();
    }
    
    public List<map_part> getNear(map_part mp) {
        List<map_part> tmp = new ArrayList<>();
        int mp_x = mp.getX();
        int mp_y = mp.getY();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <=1; j++) {
                if (i != 0 || j != 0)
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
                if (i != 0 || j != 0) {
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
    
    public path getPath(boat_table boat){
        int[][] matrix = mapToMatrix();
        for (int i = 0; i < m.height; i++)
            for (int j = 0; j < m.width; j++) {
                if (matrix[i][j] == 0)
                    matrix[i][j] = Integer.MAX_VALUE;
            }
        int fin_x = boat.getX_fin();
        int fin_y = boat.getY_fin();
        matrix[fin_y][fin_x] = 0;
        Pair p = new Pair (fin_x, fin_y);
        Queue<Pair> queue = new LinkedList<>();
        queue.add(p);
        path pth = new path();
        pth.boat_name = boat.getName();
        while ((p = queue.remove()) != null) {
            List<Pair> pairs = getNearPairs(p);
            for (Pair pair : pairs) {
                if (matrix[pair.y][pair.x] > matrix[p.y][p.x] + 1) {
                    matrix[pair.y][pair.x] = matrix[p.y][p.x] + 1;
                    queue.add(pair);
                }
            }
        }
        map_part tmp_map_part = m.map_list.get(boat.getX_cur() + boat.getY_cur());
        while (tmp_map_part.getX()!= fin_x && tmp_map_part.getY() != fin_y) {
            Pair path_pair = new Pair(tmp_map_part.getX(), tmp_map_part.getY());
            List<Pair> near = getNearPairs(path_pair);
            int dist = matrix[path_pair.y][path_pair.x];
            for (Pair pair : near) {
                if (matrix[pair.y][pair.x] < matrix[path_pair.y][path_pair.x]) {
                    path_pair.x = pair.x;
                    path_pair.y = pair.y;
                }
            }
            tmp_map_part = m.map_list.get(path_pair.x + m.width * path_pair.y);
            pth.boat_path.add(tmp_map_part);
        }
        return pth;
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
    
    
}


class Pair {
    public int x;
    public int y;
    
    public Pair () {
        x = y = 0;
    }
    
    public Pair (int x, int y) {
        this.x = x;
        this.y = y;
    }
}
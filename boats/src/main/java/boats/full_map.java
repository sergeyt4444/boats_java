/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.util.List;

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

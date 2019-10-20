/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author serge
 */
public class model {
    
    infr  inf = new infr();
    
    ArrayList<IObserver> arrO = new ArrayList<>();
    
    void update(){
        for (IObserver iObserver : arrO) {
            iObserver.update();
        }
    }
    
    public void addObserver(IObserver o){
        arrO.add(o);
    }    
    
    public List<boat_table> getBoats() {
        return inf.getBoats();
    }
    
    public ArrayList<map_part> getMap() {
        return inf.getMap();
    }
    
    public void updateBoat(boat_table boat) {
        inf.updateBoat(boat);
        update();
    }
    
    public void updateMap(map_part mp) {
        inf.updateMap(mp);
        update();
    }
    
    public void insertBoat(boat_table boat) {
        inf.insertBoat(boat);
        update();
    }
    
    public void insertMap(map_part mp) {
        inf.insertMap(mp);
        update();
    }
    
    public void deleteBoat(boat_table boat) {
        inf.deleteBoat(boat);
        update();
    }
    
    public void clearMap() {
        inf.clearMap();
        update();
    }
    
}

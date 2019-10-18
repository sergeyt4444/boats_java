/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;



/**
 *
 * @author serge
 */
public class map_part {
    
    private int id;
    
    private int x,y;
    
    private int isWater;
    
    private String cur_boat;

    map_part(int i, int i0) {
        x = i;
        y = i0;
        isWater = 1;
        cur_boat = "";
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIsWater() {
        return isWater;
    }

    public String getCur_boat() {
        return cur_boat;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setIsWater(int isWater) {
        this.isWater = isWater;
    }

    public void setCur_boat(String cur_boat) {
        this.cur_boat = cur_boat;
    }
    
    
    
}

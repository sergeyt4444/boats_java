/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import javax.persistence.*;



/**
 *
 * @author serge
 */
@Table (name = "map")
@Entity
public class map_part {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column (name = "x")
    private int x;
    
    @Column (name = "y")
    private int y;
    
    @Column (name = "isWater")
    private int isWater;
    
    @Transient
    private String cur_boat;
    
    public map_part() {
        x = y = 0;
        isWater= 1;
        cur_boat = "";
    }

    public map_part(int i, int i0) {
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
    
    @Override
    public String toString() {
        return "Coordinates: (" + this.x + ", " + this.y + ((this.isWater == 1)?"), block of water":"), block of land") 
                + ((cur_boat.equals(""))?", is not occupied": ", occupied by boat " + cur_boat);
    }
    
    
}

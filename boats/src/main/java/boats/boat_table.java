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

@Table (name = "boats")
@Entity
public class boat_table {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column (name = "name")
    private String name;
    
    @Column (name = "x_cur")
    private int x_cur;
    
    @Column (name = "y_cur")
    private int y_cur;
    
    @Column (name = "x_fin")
    private int x_fin;
    
    @Column (name = "y_fin")
    private int y_fin;
    
    public boat_table() {
        
    }
    
    public boat_table(String name, int x1, int y1, int x2, int y2) {
        if (x1 >= 0 && x2 >= 0 && y1 >= 0 && y2 >= 0)
        {
            this.name = name;
            x_cur = x1;
            y_cur = y1;
            x_fin = x2;
            y_fin = y2;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getX_cur() {
        return x_cur;
    }

    public void setX_cur(int x_cur) {
        this.x_cur = x_cur;
    }

    public int getY_cur() {
        return y_cur;
    }

    public void setY_cur(int y_cur) {
        this.y_cur = y_cur;
    }

    public int getX_fin() {
        return x_fin;
    }

    public void setX_fin(int x_fin) {
        this.x_fin = x_fin;
    }

    public int getY_fin() {
        return y_fin;
    }

    public void setY_fin(int y_fin) {
        this.y_fin = y_fin;
    }
    
    @Override
    public String toString() {
        return "Boat name: " + name + ", current coordinates: (" + this.x_cur + ", " + this.y_cur + "), destination: (" + this.x_fin + ", " + this.y_fin + ")";
    }
    
}

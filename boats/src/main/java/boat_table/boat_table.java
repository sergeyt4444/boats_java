/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boat_table;

import javax.persistence.*;
import boats.Pair;
import boats.path;

/**
 *
 * @author serge
 */

@Table (name = "boats")
@Entity
public class boat_table implements abstract_boat_table {
    
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

    @Transient
    public path boat_path;
    
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

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Pair getCur_Coordinates() {
        return new Pair(x_cur, y_cur);
    }

    @Override
    public Pair getFin_Coordinates() {
        return new Pair(x_fin, y_fin);
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

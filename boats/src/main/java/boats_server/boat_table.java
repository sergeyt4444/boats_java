/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats_server;

/**
 *
 * @author serge
 */
public class boat_table {
    
    private int id;
    
    private String name;
    
    private map_part current;
    
    private map_part finish;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrent(map_part current) {
        this.current = current;
    }

    public void setFinish(map_part finish) {
        this.finish = finish;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public map_part getCurrent() {
        return current;
    }

    public map_part getFinish() {
        return finish;
    }
    
}

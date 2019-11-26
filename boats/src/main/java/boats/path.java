/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boats;

import map_part.map_part;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author serge
 */
public class path {
    public Queue<map_part> boat_path;
    
    public path() {
        boat_path = new LinkedList<>();
    }

}

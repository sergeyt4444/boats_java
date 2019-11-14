package algorithm;

import boat_table.*;
import boats.path;
import full_map.*;

public interface abstract_algorithm {

    public path getPath(full_map fm, boat_table boat);
    public void moveBoat(full_map fm, path path1);

}

package boat_table;

public class BBoat_Table {

    public abstract_boat_table get_boat_table (String Type) {
        if (Type == null) {
            return null;
        }
        if (Type.equalsIgnoreCase("boat_table")) {
            return new boat_table();
        }
        return null;
    }

}

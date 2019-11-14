package full_map;

public class BFull_Map {

    public abstract_full_map create_full_map (String Type) {
        if (Type == null) {
            return null;
        }
        if (Type.equalsIgnoreCase("full_map")) {
            return new full_map();
        }
        return null;
    }
}

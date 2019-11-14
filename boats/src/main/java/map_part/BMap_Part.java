package map_part;

public class BMap_Part {
    public abstract_map_part get_map_part(String Type) {
        if (Type == null) {
            return null;
        }
        if (Type.equalsIgnoreCase("map_part")) {
            return new map_part();
        }
        return null;
    }
}

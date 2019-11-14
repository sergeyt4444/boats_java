package map;

public class BMap {

    public map get_map (String Type) {
        if (Type == null) {
            return null;
        }
        if (Type.equalsIgnoreCase("map")) {
            return new map();
        }
        return null;
    }

}

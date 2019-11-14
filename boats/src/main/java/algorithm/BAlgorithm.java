package algorithm;

public class BAlgorithm {

    public abstract_algorithm get_algorithm (String Type) {
        if (Type == null) {
            return null;
        }
        if (Type.equalsIgnoreCase("algorithm")) {
            return new algorithm();
        }
        return null;
    }

}

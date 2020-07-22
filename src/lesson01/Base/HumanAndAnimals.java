package lesson01.Base;

public abstract class HumanAndAnimals {

    boolean HumanOrAnimals;
    String name;

    int maxDistance;
    double maxHeight;

    public HumanAndAnimals(boolean humanOrAnimals, String name, int maxDistance, double maxHeight) {
        HumanOrAnimals = humanOrAnimals;
        this.name = name;
        this.maxDistance = maxDistance;
        this.maxHeight = maxHeight;
    }

    public String getName() {
        return name;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public double getMaxHeight() {
        return maxHeight;
    }

}

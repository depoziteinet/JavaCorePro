package lesson01;

import lesson01.Base.HumanAndAnimals;
import lesson01.Base.RunJump;

public class Cat extends HumanAndAnimals implements RunJump {
    boolean passed = true;

    public boolean isPassed() {
        return passed;
    }

    public void setPassed(boolean passed) {
        this.passed = passed;
    }

    public Cat(boolean humanOrAnimals, String name, int maxDistance, double maxHeight) {
        super(humanOrAnimals, name, maxDistance, maxHeight);
    }

    @Override
    public boolean Run(int distance) {
        boolean passed;
        passed = getMaxDistance() <= distance;
        System.out.println("Я (" + getName() + ", максимальная дистанция "
                + getMaxDistance() + " м.) "+ (passed ? " не ":"")
                + " пробежал " + distance + " м. !!!" + (passed ? " (Выступление закончил)":""));
    return !passed;
    }

    @Override
    public boolean Jump(double height) {
        boolean passed;
        passed = getMaxHeight() < height;
        System.out.println("Я (" + getName() + ", максимальная высота прыжка "
                + getMaxHeight() + " м.) " + (passed ? " не ":"")
                +  "перепрыгнул стену высотой " + height + " м. !!!" + (passed ? " (Выступление закончил)":""));
        return passed;
    }
}

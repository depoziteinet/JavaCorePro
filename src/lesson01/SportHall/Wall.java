package lesson01.SportHall;

import lesson01.Base.HumanAndAnimals;
import lesson01.Base.SportHall;
import lesson01.Cat;
import lesson01.Human;
import lesson01.Robot;

public class Wall implements SportHall {

    private final double height = 1.5;

    @Override
    public void DoAtGym(HumanAndAnimals[] humanAndAnimals) {
        boolean pass;
        System.out.println("\nСтена.\n");
        for (HumanAndAnimals humanAndAnimal : humanAndAnimals) {
            if (humanAndAnimal instanceof Human) {
                if(((Human) humanAndAnimal).isPassed()) {
                    pass = ((Human) humanAndAnimal).Jump(height);
                    ((Human) humanAndAnimal).setPassed(pass);
                }
            } else if (humanAndAnimal instanceof Cat) {
                if(((Cat) humanAndAnimal).isPassed()) {
                    pass = ((Cat) humanAndAnimal).Jump(height);
                    ((Cat) humanAndAnimal).setPassed(pass);
                }
            } else if (humanAndAnimal instanceof Robot) {
                if(((Robot) humanAndAnimal).isPassed()) {
                    pass = ((Robot) humanAndAnimal).Jump(height);
                    ((Robot) humanAndAnimal).setPassed(pass);
                }
            }

        }
        System.out.println("------------------------------------");
    }
}

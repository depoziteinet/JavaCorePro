package lesson01.SportHall;

import lesson01.Base.HumanAndAnimals;
import lesson01.Base.SportHall;
import lesson01.Cat;
import lesson01.Human;
import lesson01.Robot;

public class Treadmill implements SportHall {
    private final int distance = 100;

    @Override
    public void DoAtGym(HumanAndAnimals[] humanAndAnimals) {
        boolean pass;
        System.out.println("\nБеговая дорожка.\n");
        for (HumanAndAnimals humanAndAnimal : humanAndAnimals) {
            if(humanAndAnimal instanceof Human){
                if(((Human) humanAndAnimal).isPassed()) {
                    pass = ((Human) humanAndAnimal).Run(distance);
                    ((Human) humanAndAnimal).setPassed(pass);
                }
            }else if(humanAndAnimal instanceof Cat){
                if(((Cat) humanAndAnimal).isPassed()) {
                    pass = ((Cat) humanAndAnimal).Run(distance);
                    ((Cat) humanAndAnimal).setPassed(pass);
                }
            }else if(humanAndAnimal instanceof Robot){
                if(((Robot) humanAndAnimal).isPassed()) {
                    pass = ((Robot) humanAndAnimal).Run(distance);
                    ((Robot) humanAndAnimal).setPassed(pass);
                }
            }
        }
        System.out.println("------------------------------------");
    }
}

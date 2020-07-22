package lesson01;

import lesson01.Base.HumanAndAnimals;
import lesson01.Base.SportHall;
import lesson01.SportHall.Treadmill;
import lesson01.SportHall.Wall;

public class Test {
    public static void main(String[] args) {
        HumanAndAnimals[] humanAndAnimals = new HumanAndAnimals[6];
        humanAndAnimals[0] = new Human(true, "Вася", 90, 1.2 );
        humanAndAnimals[1] = new Human(true, "Петя", 120, 2.2 );
        humanAndAnimals[2] = new Cat(false, "КотЛета", 50,2.1 );
        humanAndAnimals[3] = new Cat(false, "КотЁнок", 500,1.8 );
        humanAndAnimals[4] = new Robot(false, "Вертер", 1000, 0 );
        humanAndAnimals[5] = new Robot(false, "Грамозека", 3000, 5 );

        SportHall[] sportHalls = new SportHall[2];
        sportHalls[0]  = new Treadmill();
        sportHalls[1] = new Wall();

        for (SportHall sportHall : sportHalls) {
            sportHall.DoAtGym(humanAndAnimals);
        }

    }
}

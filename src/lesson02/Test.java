package lesson02;

import java.util.Arrays;

public class Test {

    void WorkingWithArray(String[][] array) throws MyArrayDataException {
        boolean isFourFour = true;
        int count = 0;
        int summmaElements = 0;
        int numI = 0;
        int numJ = 0;

        System.out.println("------------------------------------\nРабота с массивом. Проверяем массив на размерность 4x4 и суммируем все элементы:\n");
        for (String[] strings : array) {
            if(strings.length != 4)
                isFourFour = false;
            System.out.println(Arrays.toString(strings));
            count++;
        }

        if(!isFourFour | count !=4)
            throw new MyArraySizeException("Массив размерами не 4x4 !!!\n");

        try {
            for (int i = 0; i < array.length; i++) {
                for (int j = 0; j < array[i].length; j++) {
                    numI = i;
                    numJ = j;
                    summmaElements += Integer.parseInt(array[i][j]);
                }

            }
        }catch (Exception e){
            String errText = "В ячейке массива с индексом [" + numI + "] [" + numJ + "] находиться не число !!!\n";
            throw new MyArrayDataException(errText);
        }

        System.out.println("Сумма элементов массива: " + summmaElements + "\n");
    }

    public static void main(String[] args){
        String[][] array44 = {{"1", "2", "3", "4"},{"5", "6", "7", "8"},{"4", "2", "3", "4"},{"5", "6", "7", "8"}};
        String[][] array24 = {{"1", "2", "3", "4"},{"5", "6", "7", "8"}};
        String[][] array43 = {{"1", "2", "3"},{"5", "6", "7"},{"4", "2", "3"},{"5", "6", "7"}};
        String[][] array44WithChars = {{"1", "2", "3", "4"},{"5", "6", "7", "8"},{"4", "2u", "3", "4"},{"5", "6", "7", "8"}};

        Test test = new Test();

        try {
            test.WorkingWithArray(array44);
        }catch (MyArrayDataException | MyArraySizeException e){
            System.out.println(e.toString());
        }

        try {
            test.WorkingWithArray(array24);
        }catch (MyArrayDataException | MyArraySizeException e){
            System.out.println(e.toString());
        }

        try {
            test.WorkingWithArray(array43);

        }catch (MyArrayDataException | MyArraySizeException e){
            System.out.println(e.toString());
        }

        try {
            test.WorkingWithArray(array44WithChars);
        }catch (MyArrayDataException | MyArraySizeException e){
            System.out.println(e.toString());
        }

    }

}

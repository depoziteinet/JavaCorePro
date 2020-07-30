package lesson03;

import java.util.ArrayList;
import java.util.List;

public class TelSpr {
    List<String> list = new ArrayList<>();

    void add(String family, String numTel){
        list.add(family);
        list.add(numTel);
    }

    void get(String findFamilyName){
        System.out.println("Телефоны абонента по фамилии - " + findFamilyName + ":");
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).equals(findFamilyName)) {
                System.out.println(" - " + list.get(++i));
                i++;
            }
        }
        System.out.println();
    }

    public String toString(){
        StringBuilder textSpr = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            textSpr.append(list.get(i)).append(" - ").append(list.get(++i)).append("\n");
        }
        return textSpr.toString();
    }

    public static void main(String[] args) {
        TelSpr telspr = new TelSpr();

        fillTelSpr(telspr);

        System.out.println("Телефонный справочник: \n" + telspr);

        String findfamilyName = "Петров";                           // по этой фамилии ищем телефоны
        System.out.println("Ищем телефоны абонента по фамилии " + findfamilyName + ":\n");
        telspr.get(findfamilyName);

        findfamilyName = "Сидоров";                                // по этой фамилии ищем телефоны
        System.out.println("Ищем телефоны абонента по фамилии " + findfamilyName + ":\n");
        telspr.get(findfamilyName);
    }

    private static void fillTelSpr(TelSpr telspr) {
        telspr.add("Иванов", "8 800 800 80 80");
        telspr.add("Петров", "8 900 900 90 90");
        telspr.add("Сидоров", "8 700 700 70 70");
        telspr.add("Петров", "8 600 600 60 60");
        telspr.add("Ан", "+82 600 600 60 60");
    }
}

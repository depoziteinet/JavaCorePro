package lesson03;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class Words {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Set<String> set = new HashSet<>();

        fillList(list);

        Iterator<String> iter = list.iterator();

        fillArrays(map, set, iter);

        System.out.println("Исходный массив - \n" + list.toString() + "\n");
        System.out.println("Массив из уникальных элементов - \n" + set + "\n");
        System.out.println("Массив {слово} = {кол-во повторений в исходном массиве} - \n" + map + "\n");

    }

    private static void fillList(List<String> list) {
        list.add("Восемь");
        list.add("Пять");
        list.add("Шесть");
        list.add("Три");
        list.add("Два");
        list.add("Шесть");
        list.add("Четыре");
        list.add("Пять");
        list.add("Ноль");
        list.add("Семь");
        list.add("Два");
        list.add("Пять");
        list.add("Один");
        list.add("Шесть");
        list.add("Девять");
        list.add("Пять");
    }

    private static void fillArrays(Map<String, Integer> map, Set<String> set, Iterator<String> iter) {
        while (iter.hasNext()){
            String word = iter.next();

            fillSetArray(set, word);

            fillMapArray(map, word);

        }
    }

    private static void fillSetArray(Set<String> set, String word) {
        set.add(word);
    }

    private static void fillMapArray(Map<String, Integer> map, String word) {
        Integer countWord;
        countWord = map.get(word) == null ? 0 : map.get(word);

        map.put(word, ++countWord);
    }

}

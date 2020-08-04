package lesson05;

import java.util.Arrays;

public class TwoMethods {

    static final int size = 10000000;
    static final int h = size / 2;
    static float[] a1 = new float[h];
    static float[] a2 = new float[h];

    public static void main(String[] args) throws InterruptedException {

        Method1();
        Method2();
    }

    private static void Method1() {
        float[] arr = new float[size];
        Arrays.fill(arr, 1.0f);

        System.out.println("Старт метод 1");

        ComputingArray(arr, "метод 1");

        System.out.println("Стоп метод 1\n-------------------------------------------------\n");
    }


    private static void Method2() throws InterruptedException {
        float[] arr = new float[size];
        long time1;
        long time2;
        long time3;
        long time4;

        Arrays.fill(arr, 1.0f);
        System.out.println("Старт метод 2");
        System.out.println("Начинаем делить на 2 массива");
        time1 = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        time2 = System.currentTimeMillis() - time1;
        System.out.println("Время , потраченное на разделение массива - " + time2);

        // блок запуска вычислений в два потока
        // начало
        System.out.println("\nЗапускаем вычисления поток 1");
        ComputingArray1 computingArray1 = new ComputingArray1();
        computingArray1.start();

        System.out.println("Запускаем вычисления поток 2");
        ComputingArray2 computingArray2 = new ComputingArray2();
        computingArray2.start();

        computingArray1.join();
        computingArray2.join();
        // конец
/*

        // блок запуска вычислений двух массивов ПОСЛЕДОВАТЕЛЬНО !!!
        // начало
        ComputingArray(a1, "метод 2, ПОСЛЕДОВАТЕЛЬНЫЙ поток 1");
        ComputingArray(a2, "метод 2, ПОСЛЕДОВАТЕЛЬНЫЙ поток 2");
        // конец

*/

        time3 = System.currentTimeMillis() - time1 - time2;
        System.out.println("Время , потраченное на вычисления в два потока - " + time3);
        System.out.println();
        System.out.println("Соединяем массивы");

        System.arraycopy(a1, 0, arr, 0, h);
        System.arraycopy(a2, 0, arr, h, h);

        time4 = System.currentTimeMillis() - time1 - time2 - time3;
        System.out.println("Время , потраченное на соединение массива - " + time4);
        System.out.println();

        System.out.println("Итого потрачено времени для метода 2 - " + (System.currentTimeMillis() - time1) + "\n----------------------------------------");
    }

    private static void ComputingArray(float[] arr, String text) {
        long t;
        t = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время расчёта (" + text + ") - " + (System.currentTimeMillis() - t));
    }


    static class ComputingArray1 extends Thread{

        @Override
        public void run() {
            ComputingArray(a1, "метод 2, поток 1");
        }
    }

    static class ComputingArray2 extends Thread{

        @Override
        public void run() {
            ComputingArray(a2, "метод 2, поток 2");
        }
    }


}

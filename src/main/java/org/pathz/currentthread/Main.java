package org.pathz.currentthread;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                System.out.println("Потік " + Thread.currentThread().getName() + " виконується.");
                sleepFor(500);
            }
        }, "Кіт");

        Thread thread2 = new Thread(() -> {
            while (true) {
                System.out.println("Потік " + Thread.currentThread().getName() + " виконується.");
                sleepFor(450);
            }
        }, "Собака");

        int maxThreadPriority = 5;
        thread2.setPriority(10);
        thread1.start();
        thread2.start();
    }

    private static void sleepFor(long millis) {

    }
}

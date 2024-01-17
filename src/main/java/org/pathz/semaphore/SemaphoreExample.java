package org.pathz.semaphore;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {

    private static final Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
        // Створюємо семафор з дозволом на одну потокову операцію
        Semaphore semaphore = new Semaphore(1);

        // Створюємо два потоки
        Thread thread1 = new Thread(new Worker(semaphore, "Потік 1"));
        Thread thread2 = new Thread(new Worker(semaphore, "Потік 2"));
        Thread thread3 = new Thread(new Worker(semaphore, "Потік 3"));
        Thread thread4 = new Thread(new Worker(semaphore, "Потік 4"));
        Thread monitoringThread = new Thread(() -> {
            while (true) {
                System.out.println("Довжина внутрішньої черги семафора: " + semaphore.getQueueLength());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        startAndJoinThreads(thread1, thread2, thread3, thread4, monitoringThread);
    }

    public static void startAndJoinThreads(Thread... threads) throws InterruptedException {
        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}

class Worker implements Runnable {
    private Semaphore semaphore;
    private String name;

    public Worker(Semaphore semaphore, String name) {
        this.semaphore = semaphore;
        this.name = name;
    }

    @Override
    public void run() {
        try {
            System.out.println(name + " намагається отримати дозвіл.");
            semaphore.acquire(); // Отримуємо дозвіл на доступ
            System.out.println(name + " отримав дозвіл.");

            // Виконуємо якусь роботу (симулюємо використання ресурсу)
            Thread.sleep(4500);

            System.out.println(name + " завершив роботу і відпускає дозвіл.");
            semaphore.release(); // Відпускаємо дозвіл
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


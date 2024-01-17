package org.pathz;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MultithreadCOll {
    public static void main(String[] args) throws InterruptedException {
        List<String> personNames = new CopyOnWriteArrayList<>();

        personNames.add("alex");
        personNames.add("maryna");
        personNames.add("artem");

        Thread thread1 = new Thread(() -> {
            for (String personName : personNames) {
                System.out.println(Thread.currentThread().getName() + ": " + personName);
                sleep();
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                personNames.add(String.valueOf(ThreadLocalRandom.current().nextInt()));
            }
            sleep();
        });

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

    }

    private static void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

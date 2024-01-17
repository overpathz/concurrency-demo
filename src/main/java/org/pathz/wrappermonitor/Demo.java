package org.pathz.wrappermonitor;

public class Demo {

    public static Integer counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            incrementCounter();
        });
        Thread t2 = new Thread(() -> {
            incrementCounter();
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        // counter??
        System.out.println(counter);
    }

    private static void incrementCounter() {
        synchronized (counter) {
            for (int i = 0; i < 1_000_000; i++) {
                counter++;
            }
        }
    }
}

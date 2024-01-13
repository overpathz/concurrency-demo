package org.pathz.volat;

public class VariableVisibilityExample {
    private static volatile boolean flag = false;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            // Simulate some work
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            flag = true; // Set the flag to true
        });

        Thread thread2 = new Thread(() -> {
            while (!flag) {
                // Wait for the flag to become true
            }
            System.out.println("Flag is true. Exiting.");
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
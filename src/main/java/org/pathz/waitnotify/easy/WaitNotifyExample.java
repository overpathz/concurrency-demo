package org.pathz.waitnotify.easy;

import java.util.ArrayList;
import java.util.List;

public class WaitNotifyExample {
    private static final List<Integer> list = new ArrayList<>();

    public static void main(String[] args) {
        Thread threadA = new Thread(() -> {
            synchronized (list) {
                try {
                    System.out.println("Thread A: Waiting...");
                    list.wait(); // Потік A чекає
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread A: Resumed.");
            }
        });

        Thread threadB = new Thread(() -> {
            synchronized (list) {
                System.out.println("Thread B: Adding an element.");
                list.add(42);
                list.notify(); // Потік B сповіщає потік A
            }
        });

        threadA.start();
        threadB.start();
    }
}


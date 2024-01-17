package org.pathz.te;

public class Min {
    static final Object lock = new Object();

    public static void main(String[] args) {

        synchronized (lock) {
            // виконанння критичної секції
            System.out.println();
        }
    }
}

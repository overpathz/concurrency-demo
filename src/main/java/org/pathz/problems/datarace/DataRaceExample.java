package org.pathz.problems.datarace;

public class DataRaceExample {

    static int sharedVariable = 0;

    public static void main(String[] args) {

        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                setX(3);
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                setX(7);
            }
        };

        new Thread(task1).start();
        new Thread(task2).start();
    }

    public static void setX(int x) {
        sharedVariable = x;
    }
}

package org.pathz.syncone;

public class SyncDemo {
    public static void main(String[] args) {
        Test test = new Test();

        final Object lock1 = new Object();
        final Object lock2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (lock1) {
                test.do1();
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (lock1) {
                test.do1();
            }
        });


        Thread t3 = new Thread(() -> {
            synchronized (lock2) {
                test.doCompletelyOtherThing();
            }
        });
        Thread t4 = new Thread(() -> {
            synchronized (lock2) {
                test.doCompletelyOtherThing();
            }
        });

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        // тримається лок на одному ж і тому об'єкті
        // рішення? створити декілька локів специфічний під бізнес-логіку
    }
}

class Test {
    // 1 потік має тут виконуватися
    public void do1() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + ": " + "Do smtj");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public synchronized void workWithAccount(String username) {
        // ...
        // ...
        // ...


        // ...
    }

    // 1 потік має тут виконуватися
    public void doCompletelyOtherThing() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " : " + "do completaly other thing");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

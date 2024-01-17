package org.pathz.lock;

import lombok.RequiredArgsConstructor;

import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.*;

public class Rs {

    public static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(new MyRunnable(lock));
        Thread t2 = new Thread(new MyRunnable(lock));
        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println(counter);
    }

    public static void increment() {
        for (int i = 0; i < 1_000_000; i++) {
            if (i == 500_000) {
                throw new RuntimeException("BOOH!");
            }
            counter++;
        }
    }
}

@RequiredArgsConstructor
class MyRunnable implements Runnable {
    private final Lock lock;

    @Override
    public void run() {
        try {
            lock.tryLock(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Rs.increment();
        lock.unlock();
    }
}

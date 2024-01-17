package org.pathz;

import javafx.scene.Parent;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class RealExecutorExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
       ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> submit = executorService.submit(new MySuperCalleble());
    }

    public static void increment() {

    }
}


class MySuperRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Running task..");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Running processed.");
    }
}

class MySuperCalleble implements Callable<String> {

    @Override
    public String call() throws Exception {
        System.out.println("Running task..");
        try {
            Thread.sleep(10_000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Running processed.");
        return "result!";
    }
}
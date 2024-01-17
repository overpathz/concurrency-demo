package org.pathz.waitnotify.harder;

import lombok.Data;

import java.util.Queue;
import java.util.concurrent.*;

public class ProducerConsumerWIthQUeue {
    private static final BlockingQueue<Integer> buffer = new PriorityBlockingQueue<>(10);

    public static void main(String[] args) {
        Thread producerThread = new Thread(() -> {
            while (true) {
                produce();
            }
        });

        Thread consumerThread = new Thread(() -> {
            while (true) {
                consume();
            }
        });

        producerThread.start();
        consumerThread.start();
    }

    private static void produce() {
        int nextInt = ThreadLocalRandom.current().nextInt(100);
        System.out.println("Producer: " + nextInt);
        try {
            buffer.put(nextInt);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void consume() {
        Integer poll = null;
        try {
            poll = buffer.poll(1, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Consume: " + poll);
    }
}

@Data
class Request {
    private long timestamp;

}


package org.pathz.waitnotify.harder;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumer {
    private static final int BUFFER_SIZE = 5;
    private static final Queue<Integer> buffer = new LinkedList<>();

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
        synchronized (buffer) {
            while (buffer.size() == BUFFER_SIZE) {
                try {
                    System.out.println("Буфер заповнено. Чекаю щоб з нього щось взяли..");
                    buffer.wait(); // Буфер повний, виробник зачекає
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int item = (int) (Math.random() * 100);
            buffer.offer(item); // кладемо елемент
            System.out.println("Produced: " + item);
            buffer.notify(); // Повідомлення споживачу
        }
    }

    private static void consume() {
        synchronized (buffer) {
            while (buffer.isEmpty()) {
                try {
                    System.out.println("Буфер пустий. Чекаю щоб в нього щось поклали.");
                    buffer.wait(); // Буфер порожній, споживач зачекає
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            int item = buffer.poll(); // беремо елемент
            System.out.println("Consumed: " + item);
            buffer.notify(); // Повідомлення виробнику
        }
    }
}


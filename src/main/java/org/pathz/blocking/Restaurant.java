package org.pathz.blocking;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Restaurant {
    private static final int NUM_TABLES = 5;
    private static final ExecutorService executorService = Executors.newFixedThreadPool(NUM_TABLES);

    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();


        while (true) {
            try {
                Order order = restaurant.acceptOrder(); // <---- в цей момент прилітають нові замовлення
                new Thread(() -> handleOrder(order)).start();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    private static void handleOrder(Order order) {
        System.out.println("Обробка замовлення #" + order.getId());
        while (true) {
            //
        }
    }

    public synchronized Order acceptOrder() throws InterruptedException {
        // Симуляція блокуючого виклику accept
        System.out.println("Очікування замовлення від клієнта..");
        TimeUnit.SECONDS.sleep(2); // чекаємо
        System.out.println("Клієнт зробив замовлення.");
        return new Order(); // Повернення якогось замовлення
    }
}

class Order {
    private static long orderCounter;
    private final long id;

    public Order() {
        this.id = ++Order.orderCounter;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                '}';
    }
}


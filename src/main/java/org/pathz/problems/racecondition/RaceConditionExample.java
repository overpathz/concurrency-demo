package org.pathz.problems.racecondition;

import java.util.HashMap;
import java.util.Map;

public class RaceConditionExample {
    public static void main(String[] args) {
        Map<String, String> rentCars = new HashMap<>();
        rentCars.put("..", "..");

        // машина для оренди <-> якийсь користувач

        // thread 1
        if (!rentCars.containsKey("BMW x5")) {
            rentCars.put("BMW x5", "user1");
        }

        // thread 2
        if (!rentCars.containsKey("BMW x5")) {
            rentCars.put("BMW x5", "user3");
        }

        Object lock = new Object();
        synchronized (lock) {
            // ...
        }
    }
}

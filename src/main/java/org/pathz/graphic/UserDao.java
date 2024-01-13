package org.pathz.graphic;

public class UserDao {
    public static String findUserByUsername(String username) throws InterruptedException {
        Thread.sleep(3000);
        return "Alex - Java Dev";
    }
}

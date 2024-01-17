package org.pathz;

import java.util.LinkedList;
import java.util.List;

public class Demom {

    static int x = 0;

    public static void main(String[] args) throws InterruptedException {
        List<String> stringList = new LinkedList<>();
    }
}

interface MyFuncInterface {
    int getWordCount(String word);
}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        // code to run
    }
}


class MyThread extends Thread {
    @Override
    public void run() {
        System.out.println("DOING SMTH USEFUL");
    }
}
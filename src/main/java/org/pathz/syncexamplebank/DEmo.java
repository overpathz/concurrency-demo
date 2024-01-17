package org.pathz.syncexamplebank;

public class DEmo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000);

        // Створення двох потоків для зняття та внесення грошей
        Thread withdrawThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(200);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread depositThread = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(100);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        withdrawThread.start();
        depositThread.start();
    }
}

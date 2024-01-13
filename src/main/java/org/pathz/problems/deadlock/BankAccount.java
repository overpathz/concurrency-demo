package org.pathz.problems.deadlock;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

public class BankAccount {
    private int balance;

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(int amount) {
        synchronized (this) {
            balance += amount;
        }
    }

    public void withdraw(int amount) {
        synchronized (this) {
            balance -= amount;
        }
    }

    public int getBalance() {
        synchronized (this) {
            return balance;
        }
    }

    public static void transfer(BankAccount fromAccount, BankAccount toAccount, int amount) {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                    System.out.println("Transfer successful!");
                } else {
                    System.out.println("Insufficient funds for transfer.");
                }
            }
        }
    }

    public static void main(String[] args) {
        BankAccount accountA = new BankAccount(1000);
        BankAccount accountB = new BankAccount(2000);

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                BankAccount.transfer(accountA, accountB, 500);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                BankAccount.transfer(accountB, accountA, 300);
            }
        });

        Thread thread3 = new Thread(() -> {
            while (true) {
                ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
                long[] threadIds = threadBean.findDeadlockedThreads();
                if (threadIds != null) {
                    ThreadInfo[] threadInfos = threadBean.getThreadInfo(threadIds);
                    for (ThreadInfo info : threadInfos) {
                        System.out.println(info.getThreadName() + " [] " + info.getThreadState().name());
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Account A balance: " + accountA.getBalance());
        System.out.println("Account B balance: " + accountB.getBalance());
    }
}

/*

 */


package org.pathz.syncexamplebank;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    // Метод для зняття грошей з рахунку
    public synchronized void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Знято " + amount + " грн. Залишок на рахунку: " + balance + " грн.");
        } else {
            System.out.println("Недостатньо коштів на рахунку для зняття " + amount + " грн.");
        }
    }

    // Метод для внесення грошей на рахунок
    public synchronized void deposit(double amount) {
        balance += amount;
        System.out.println("Внесено " + amount + " грн. Залишок на рахунку: " + balance + " грн.");
    }
}


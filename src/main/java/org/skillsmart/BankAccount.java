package org.skillsmart;

public class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
        }
    }

    public double getBalance() {
        return balance;
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(100);

        // Попытка снять деньги.
        account.withdraw(150);

        // Ожидаемый баланс: 100, потому что 150 > 100.
        // Однако, метод withdraw позволяет произвести
        // операцию, если сумма <= баланса.
        // В данном случае, сумма позволяет произвести
        // операцию, но она не снимается,
        // так что баланс остается 100.
        System.out.println("Баланс: " + account.getBalance());

        // Попытка положить деньги.
        account.deposit(-50);

        // Ожидаемо, что баланс должен остаться прежним,
        // т.к. нельзя положить отрицательную сумму.
        // Но из-за логической ошибки в
        // методе deposit, мы видим что
        // баланс может быть неправильным.
        System.out.println("Баланс: " + account.getBalance());
    }
}
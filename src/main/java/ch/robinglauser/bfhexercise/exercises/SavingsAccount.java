package ch.robinglauser.bfhexercise.exercises;

public class SavingsAccount {

    private double balance;
    private double interest;

    /**
     * Constructs a bank account with a zero balance.
     */
    public SavingsAccount() {
        balance = 0;
    }

    /**
     * Constructs a bank account with a given balance.
     *
     * @param initialBalance the initial balance
     * @param interest       the initial interest
     */
    public SavingsAccount(double initialBalance, double interest) {
        balance = initialBalance;
        this.interest = interest;
    }

    /**
     * Deposits money into the bank account.
     *
     * @param amount the amount to deposit
     */
    public void deposit(double amount) {
        balance = balance + amount;
    }

    /**
     * Withdraws money from the bank account.
     *
     * @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        balance = balance - amount;
    }

    /**
     * Gets the current balance of the bank account.
     *
     * @return the current balance
     */
    public double getBalance() {
        return balance;
    }


    /**
     * Add interest
     */
    public void addInterest() {
        balance = balance + (balance*interest/100);
    }


    /**
     * @return current interest
     */
    public double getInterest() {
        return interest;
    }
}

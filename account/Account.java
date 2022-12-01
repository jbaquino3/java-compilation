package account;

import java.text.NumberFormat;

public class Account implements Lockable
{
    private final double RATE = 0.035; // interest rate of 3.5%
    private String name;
    private long acctNumber;
    private double balance;
    // Values for the lockable interface
    private int key;
    private boolean isLocked;

    //-----------------------------------------------------------------
    // Sets up this account with the specified owner, account number,
    // and initial balance.
    //-----------------------------------------------------------------
    public Account(String owner, long account, double initial)
    {
        name = owner;
        acctNumber = account;
        balance = initial;
        isLocked = false;
    }

    //-----------------------------------------------------------------
    // Deposits the specified amount into this account and returns
    // the new balance. The balance is not modified if the deposit
    // amount is invalid.
    //-----------------------------------------------------------------
    public double deposit(double amount) throws IllegalStateException
    {
        if(locked()) {
            throw new IllegalStateException("Account is locked.");
        } else {
            if (amount > 0)
                balance = balance + amount;
            return balance;
        }
    }

    //-----------------------------------------------------------------
    // Withdraws the specified amount and fee from this account and
    // returns the new balance. The balance is not modified if the
    // withdraw amount is invalid or the balance is insufficient.
    //-----------------------------------------------------------------
    public double withdraw(double amount, double fee) throws IllegalStateException
    {
        if(locked()) {
            throw new IllegalStateException("Account is locked.");
        } else {
            if (amount+fee > 0 && amount+fee < balance)
                balance = balance - amount - fee;
            return balance;
        }
    }

    //-----------------------------------------------------------------
    // Adds interest to this account and returns the new balance.
    //-----------------------------------------------------------------
    public double addInterest() throws IllegalStateException
    {
        if(locked()) {
            throw new IllegalStateException("Account is locked.");
        } else {
            balance += (balance * RATE);
            return balance;
        }
    }

    //-----------------------------------------------------------------
    // Returns the current balance of this account.
    //-----------------------------------------------------------------
    public double getBalance() throws IllegalStateException
    {
        if(locked()) {
            throw new IllegalStateException("Account is locked.");
        } else {
            return balance;
        }
    }

    //-----------------------------------------------------------------
    // Returns a one-line description of this account as a string.
    //-----------------------------------------------------------------
    public String toString() throws IllegalStateException
    {
        if(locked()) {
            throw new IllegalStateException("Account is locked.");
        } else {
            NumberFormat fmt = NumberFormat.getCurrencyInstance();
            return (acctNumber + "\t" + name + "\t" + fmt.format(balance));
        }
    }

    // Override interface methods
    public void setKey(int key) throws IllegalStateException {
        if(locked()) {
            throw new IllegalStateException("Account is locked.");
        } else {
            this.key = key;
        }
    }

    public void lock(int key) throws IllegalStateException {
        if(this.key == key) {
            isLocked = true;
        } else {
            throw new IllegalStateException("Incorrect key.");
        }
    }

    public void unlock(int key) throws IllegalStateException {
        if(this.key == key) {
            isLocked = false;
        } else {
            throw new IllegalStateException("Incorrect key.");
        }
    }

    public boolean locked() {
        return isLocked;
    }
}
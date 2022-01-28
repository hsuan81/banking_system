// import java.util.*; not use

public class Transaction {

    public boolean checkInput(double amount) {
        if (amount >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public double deposit(double balance, double amount) {
        return balance += amount;
        // return balance;
    }

    public boolean checkWithdraw(double balance, double amount) {
        return (amount <= balance);
    }

    public double withdraw(double balance, double amount) {
        return balance -= amount;
        // return balance;
    }

    // public void transfer(double fromBalance, double toBalance, double amount) {

    //     frombalance -= amount;
    //     toAccount.deposit(amount);
    //     // return new double[] {fromBalance, toBalance};
    // }

}

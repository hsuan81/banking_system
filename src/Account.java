import java.text.DecimalFormat;
import java.util.*;

public class Account {

	private int customerNumber;
	private int pinNumber;
    private String customerName;
	protected double balance = 0;

	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    // Transaction accountTrans = new Transaction();

	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}

	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getCustomerNumber() {
		return customerNumber;
	}

    public int getPinNumber() {
		return pinNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public double getBalance() {
		return balance;
	}

    private double deposit(double amount) {
        return this.balance += amount;
        // return balance;
    }

    private double withdraw(double amount) {
        return this.balance -= amount;
        // return balance;
    }

    private boolean checkWithdraw(double amount) {
        return (amount <= this.balance);
    }

    private boolean checkInput(double amount) {
        if (amount >= 0) {
            return true;
        }
        else {
            return false;
        }
    }

	public void calcWithdraw() {
        System.out.println("Account Balance: " + moneyFormat.format(balance));
		System.out.print("Amount you want to withdraw from Account: ");
		double amount = input.nextDouble();
        while (!checkInput(amount)) {
            System.out.println("Input is valid, please type valid amount.");
            System.out.print("Amount you want to withdraw from Account: ");
            amount = input.nextDouble();
        }

        if (checkWithdraw(amount)) {
            withdraw(amount);
            System.out.println("Withdraw succeeded.");
        }
        else {
            System.out.println("Not enough balance.");
        }
	}

	public void calcDeposit() {
        System.out.println("Account Balance: " + moneyFormat.format(balance));
		System.out.print("Amount you want to deposit to Account: ");
		double amount = input.nextDouble();

        while (!checkInput(amount)) {
            System.out.println("Input is valid, please type valid amount.");
            System.out.print("Amount you want to withdraw from Account: ");
            amount = input.nextDouble();
        }

		deposit(amount);
        System.out.println("Deposit succeeded.");
	}

    public void calcTransfer(DataPool<Account> pool) {
        System.out.println("Account Balance: " + moneyFormat.format(balance));
		System.out.print("Amount you want to transfer from Account: ");
		double amount = input.nextDouble();

        while (!checkInput(amount)) {
            System.out.println("Input is valid, please type valid amount.");
            System.out.print("Amount you want to transfer from Account: ");
            amount = input.nextDouble();
        }

        if (checkWithdraw(amount)) {
            withdraw(amount);
        }
        else {
            System.out.println("Not enough balance.");
        }
        
        System.out.print("Account number you want to transfer to: ");
        int toNumber = input.nextInt();
        // There should be a verification of the account number, but the info might be retrieved from the database
        // do the deposit to the account
        if (pool.check(toNumber)) {
            Account toAccount = pool.get(toNumber);
            toAccount.deposit(amount);
        }
        else {
            System.out.println("Account number is incorrect.");
        }

    }

	// public double calcSavingDeposit(double amount) {
	// 	savingBalance = (savingBalance + amount);
	// 	return savingBalance;
	// }

	// public void getCheckingWithdrawInput() {
	// 	System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
	// 	System.out.print("Amount you want to withdraw from Checking Account: ");
	// 	double amount = input.nextDouble();

	// 	if ((checkingBalance - amount) >= 0) {
	// 		calcCheckingWithdraw(amount);
	// 		System.out.println("New Checking Account Balance: " + moneyFormat.format(checkingBalance));
	// 	} else {
	// 		System.out.println("Balance Cannot be Negative." + "\n");
	// 	}
	// }

	// public void getsavingWithdrawInput() {
	// 	System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
	// 	System.out.print("Amount you want to withdraw from saving Account: ");
	// 	double amount = input.nextDouble();

	// 	if ((savingBalance - amount) >= 0) {
	// 		calcSavingWithdraw(amount);
	// 		System.out.println("New saving Account Balance: " + moneyFormat.format(savingBalance));
	// 	} else {
    //         System.out.println("Balance Cannot be Negative." + "\n");
	// 	}
	// }

	// public void getCheckingDepositInput() {
	// 	System.out.println("Checking Account Balance: " + moneyFormat.format(checkingBalance));
	// 	System.out.print("Amount you want to Deposit from Checking Account: ");
	// 	double amount = input.nextDouble();

	// 	if ((balance + amount) >= 0) {
	// 		calcCheckingDeposit(amount);
	// 		System.out.println("New Checking Account Balance: " + moneyFormat.format(checkingBalance));
	// 	} else {
	// 		System.out.println("Balance Cannot be Negative." + "\n");
	// 	}
	// }

	// public void getSavingDepositInput() {
	// 	System.out.println("Saving Account Balance: " + moneyFormat.format(savingBalance));
	// 	System.out.print("Amount you want to Deposit from saving Account: ");
	// 	double amount = input.nextDouble();

	// 	if ((savingBalance + amount) >= 0) {
	// 		calcSavingDeposit(amount);
	// 		System.out.println("New saving Account Balance: " + moneyFormat.format(savingBalance));
	// 	} else {
	// 		System.out.println("Balance Cannot be Negative." + "\n");
	// 	}
	// }

}



public class Account {

	private String customerNumber;
	private String pinNumber;
    private String customerName;
	protected double balance = 0;

	public String setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}

	public String setPinNumber(String pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerNumber() {
		return customerNumber;
	}

    public String getPinNumber() {
		return pinNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public double getBalance() {
		return balance;
	}

    public double deposit(double amount) {
        return balance += amount;
    }

    public double withdraw(double amount) {
        return balance -= balance;
    }

    public boolean checkWithdraw(double amount) {
        return (amount <= this.balance);
    }

    public boolean checkInput(double amount) {
        if (amount >= 0) {
            return true;
        }
        else {
            return false;
        }
    }
}



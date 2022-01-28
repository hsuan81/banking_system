import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.*;
import java.io.IOException;

public class OptionMenu extends Account {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");
    
    // use database to store user info instead of a hashmap//
	HashMap<Integer, Integer> data = new HashMap<Integer, Integer>();
	// read customer datapool
	ReadWriteToFile<Profile> profileData = new ReadWriteToFile<>("./profile.txt");
	ReadWriteToFile<Account> accountData = new ReadWriteToFile<>("./account.txt");
	DataPool<Profile> profilePool;
	DataPool<Account> accountPool;
	Account account;
	Profile profile;

	public OptionMenu() {
		// need to check if file exists
		profilePool = profileData.read();
		accountPool = accountData.read();
	}

	public void home() {
		System.out.println("Welcome to the ABC BANK!");
		System.out.println("1. Log in");
		System.out.println("2. Create account");
		System.out.println("Please type your option below:");

		int selection = menuInput.nextInt();

		switch (selection) {
			case 1:
				getLogin();
				break;
	
			case 2:
				createAccount();
				break;
	
			default:
				System.out.println("\n" + "Invalid Choice." + "\n");
				home();;
			}
	}

	public void getLogin() throws IOException {
		int x = 1;
		do {
			try {
                // data.put(952141, 191904);
				// data.put(989947, 71976);

				
				System.out.println("Enter your customer Number");
				setCustomerNumber(menuInput.nextInt());

				System.out.print("Enter your PIN Number: ");
				setPinNumber(menuInput.nextInt());
			} catch (Exception e) {
				System.out.println("\n" + "Invalid Character(s). Only Numbers." + "\n");
				x = 2;
			}
			/*
			 * for(Map.Entry<Integer,Integer> it : data.entrySet()){
			 * if(it.getkey()==getCustomerNumber() && it.getValue()==getPinNumber){
			 * getAccountType(); } }
			 */
			int cn = getCustomerNumber();
			int pn = getPinNumber();
			if (accountPool.check(cn)) {
				if (accountPool.get(cn).getPinNumber() == pn){
					account = accountPool.get(cn);
					getMenu();
				}
				else{
					System.out.println("\n" + "Wrong Pin Number" + "\n");
				}
				
			} else {	
				System.out.println("\n" + "Wrong Customer Number or Please create an account" + "\n");
			}
				
		} while (x == 1);
	}

	public void createAccount() {
		account = new Account();
		System.out.println("Enter your 10 digit customer Number");
		account.setCustomerNumber(menuInput.nextInt());

		System.out.print("Enter your 8 digit PIN Number: ");
		account.setPinNumber(menuInput.nextInt());

		System.out.print("Enter your Name: ");
		account.setCustomerName(menuInput.nextLine());

		profile = new Profile();
		System.out.print("Enter your Phone Number: ");
		profile.editPhoneNumber(menuInput.nextLine());

		System.out.print("Enter your Address: ");
		profile.editAddress(menuInput.nextLine());

		System.out.print("Enter your Email: ");
		profile.editEmailAddress(menuInput.nextLine());

		// store to the data pool
		accountPool.put(account.getCustomerNumber(), account);
		profilePool.put(account.getCustomerNumber(), profile);

	}

	public void getMenu() {
		System.out.println("Select the Account you Want to Access: ");
		System.out.println(" Type 1 - Transaction");
		System.out.println(" Type 2 - Profile");
		System.out.println(" Type 3 - Exit");

		int selection = menuInput.nextInt();

		switch (selection) {
		case 1:
			getTransaction();
			break;

		case 2:
			getProfile();
            break;

		case 3:
			System.out.println("Thank You for using this ATM, bye.  \n");
			break;

		default:
			System.out.println("\n" + "Invalid Choice." + "\n");
			getMenu();
		}
	}

	public void getTransaction() {
		System.out.println("Transaction: ");
		System.out.println(" Type 1 - Deposit");
		System.out.println(" Type 2 - Withdraw");
		System.out.println(" Type 3 - Transfer");
		System.out.println(" Type 4 - Exit");
		System.out.print("Choice: ");

		int selection = menuInput.nextInt();

		switch (selection) {
		case 1:
			account.calcDeposit();
			// System.out.println("Checking Account Balance: " + moneyFormat.format(getCheckingBalance()));
			getMenu();
			break;

		case 2:
			account.calcWithdraw();
			getMenu();
			break;

		case 3:
			account.calcTransfer(accountPool);
			getMenu();
			break;

		case 4:
			System.out.println("Thank You for using this ATM, bye.");
			break;

		default:
        System.out.println("\n" + "Invalid Choice." + "\n");
        getTransaction();
    }
}

    public void getProfile() {
        System.out.println("Profile: ");
        System.out.println(" Type 1 - View Balance");
        System.out.println(" Type 2 - Withdraw Funds");
        System.out.println(" Type 3 - Deposit Funds");
        System.out.println(" Type 4 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch (selection) {
        case 1:
            System.out.println("Saving Account Balance: " + moneyFormat.format(getSavingBalance()));
            getAccountType();
            break;

        case 2:
            getsavingWithdrawInput();
            getAccountType();
            break;

        case 3:
            getSavingDepositInput();
            getAccountType();
            break;

        case 4:
            System.out.println("Thank You for using this ATM, bye.");
            break;

        default:
            System.out.println("\n" + "Invalid Choice." + "\n");
            getChecking();
        }
    }

}






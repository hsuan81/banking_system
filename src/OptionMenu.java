import java.text.DecimalFormat;
import java.util.*;
import java.io.IOException;
import java.io.File;
import com.google.gson.reflect.TypeToken;

public class OptionMenu {
	Scanner menuInput = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

	// read customer datapool
	String profilePath = "./profile.txt";
	String accountPath = "./account.txt";
	ReadWriteToFile<Profile> profileData = new ReadWriteToFile<>(profilePath, new TypeToken<DataPool<Profile>>() { });
	ReadWriteToFile<Account> accountData = new ReadWriteToFile<>(accountPath, new TypeToken<DataPool<Account>>() { });
	DataPool<Profile> profilePool;
	DataPool<Account> accountPool;
	Account account;
	Profile profile;

	public void initDataPool() {
		// check if file exists
		File profileFile = new File(profilePath);
		File accountFile = new File(accountPath);
		
		try {
			if (!profileFile.createNewFile()) {
				profilePool = profileData.read();
			}
			if (profilePool == null) {
				profilePool = new DataPool<Profile>();
			}
			if (!accountFile.createNewFile()) {
				accountPool = accountData.read();
			}
			if (accountPool == null) {
				accountPool = new DataPool<Account>();
			}

		} catch (IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}	
		
	}

	public void home() {
		System.out.println("Welcome to the ABC BANK!");
		System.out.println("1. Log in");
		System.out.println("2. Create account");
		System.out.println("Please type your option below:");

		int selection = menuInput.nextInt();
		menuInput.nextLine();

		switch (selection) {
			case 1:
				try {
					getLogin();
				} catch (IOException e) {
					System.out.println("Something wrong!");
					e.printStackTrace();
				}
				break;
	
			case 2:
				createAccount();
				getMenu();
				break;
	
			default:
				System.out.println("\n" + "Invalid Choice." + "\n");
				home();;
			}
	}

	public void exitApp() {
		profileData.write(profilePool);
		accountData.write(accountPool);
	}

	public void getLogin() throws IOException {
		boolean reAsk = false;
		String inputNumber = "";
		String inputPin = "";
		do {
			try {
				reAsk = false;
				System.out.println("Enter your customer Number");
				inputNumber = menuInput.nextLine();
				

				System.out.print("Enter your PIN Number: ");
				inputPin = menuInput.nextLine();

				
			
			} catch (Exception e) {
				System.out.println("\n" + "Invalid Character(s). Only Numbers." + "\n");
				menuInput.next();
				reAsk = true;
			}
		} while (reAsk);

		// if account pool is null, the data file is empty
		if (accountPool == null){
			System.out.println("Please create an account");
			home();
		}
		else{
			System.out.println("account pool is assigned");
		
			if (accountPool.check(inputNumber)) {
				if (accountPool.get(inputNumber).getPinNumber().equals(inputPin)){
					account = accountPool.get(inputNumber);
					profile = profilePool.get(inputNumber);
					getMenu();
				}
				else{
					System.out.println("\n" + "Wrong Pin Number" + "\n");
					home();
				}
				
			} else {	
				System.out.println("\n" + "Wrong Customer Number or Please create an account" + "\n");
				home();
			}
		}
	}

	public void createAccount() {
		account = new Account();
		System.out.println("Enter your customer Number");
		account.setCustomerNumber(menuInput.nextLine());

		System.out.print("Enter your PIN Number: ");
		account.setPinNumber(menuInput.nextLine());

		System.out.print("Enter your Name: ");
		account.setCustomerName(menuInput.nextLine());
		
		profile = new Profile();
		System.out.print("\n" + "Enter your Phone Number: ");
		profile.setPhoneNumber(menuInput.nextLine());

		System.out.print("Enter your Address: ");
		profile.setAddress(menuInput.nextLine());

		System.out.print("Enter your Email: ");
		profile.setEmailAddress(menuInput.nextLine());

		// store data to the data pool
		accountPool.put(account.getCustomerNumber(), account);
		profilePool.put(account.getCustomerNumber(), profile);
		System.out.println("Account created successfully. You can start to use our services.");
	}

	public void getMenu() {
		System.out.println("Select the Action you want to take: ");
		System.out.println(" Type 1 - Transaction");
		System.out.println(" Type 2 - Profile and Balance");
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
			exitApp();
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
		System.out.println(" Type 4 - Back");
		System.out.println(" Type 5 - Exit");
		System.out.print("Choice: ");

		int selection = menuInput.nextInt();
		menuInput.nextLine();

		switch (selection) {
			case 1:
				getDeposit();
				getMenu();
				break;

			case 2:
				getWithdraw();
				getMenu();
				break;

			case 3:
				getTransfer();
				getMenu();
				break;

			case 4:
				getProfile();
				break;

			case 5:
				exitApp();
				System.out.println("Thank You for using this ATM, bye.");
				break;

			default:
			System.out.println("\n" + "Invalid Choice." + "\n");
			getTransaction();
		}
	}

	public void getWithdraw() {
		System.out.println("Account Balance: " + moneyFormat.format(account.getBalance()));
		System.out.print("Amount you want to withdraw from Account: ");
		double amount = menuInput.nextDouble();
        if (!account.checkInput(amount)) {
            System.out.println("Input is valid, please type valid amount.");
			getWithdraw();
		}
        
		if (account.checkWithdraw(amount)) {
            account.withdraw(amount);
            System.out.println("Withdraw succeeded.");
        }
        else {
            System.out.println("Not enough balance.");
        }
	}

	public void getDeposit() {
		System.out.println("Account Balance: " + moneyFormat.format(account.getBalance()));
		System.out.print("Amount you want to deposit to Account: ");
		double amount = menuInput.nextDouble();

        while (!account.checkInput(amount)) {
            System.out.println("Input is valid, please type valid amount.");
            System.out.print("Amount you want to withdraw from Account: ");
            amount = menuInput.nextDouble();
        }
		account.deposit(amount);
		System.out.println("Deposit succeeded.");
	}

	public void getTransfer() {
		System.out.println("Account Balance: " + moneyFormat.format(account.getBalance()));
		System.out.print("Amount you want to transfer from Account: ");
		double amount = menuInput.nextDouble();

        while (!account.checkInput(amount)) {
            System.out.println("Input is valid, please type valid amount.");
            System.out.print("Amount you want to transfer: ");
            amount = menuInput.nextDouble();
        }

        if (!account.checkWithdraw(amount)) {
			System.out.println("Not enough balance.");
        }
        else {
			menuInput.nextLine();
			System.out.print("Account number you want to transfer to: ");
			String toNumber = menuInput.nextLine();

			// Verify the targeted account number, and the number must not be the same as the sender's
			if (accountPool.check(toNumber) && !toNumber.equals(account.getCustomerNumber())) {
				Account toAccount = accountPool.get(toNumber);
				account.withdraw(amount);
				toAccount.deposit(amount);
				System.out.println("Transfer succeeded.");
			}
			else {
				System.out.println("Account number is incorrect.");
			}
		}
	}

    public void getProfile() {
        System.out.println("Profile and Balance: ");
        System.out.println(" Type 1 - View Balance");
        System.out.println(" Type 2 - View Profile");
        System.out.println(" Type 3 - Edit Profile");
        System.out.println(" Type 4 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

        switch (selection) {
        case 1:
            System.out.println("Account Balance: " + moneyFormat.format(account.getBalance()));
            getMenu();
            break;

        case 2:
            profile.viewProfile();
            getMenu();
            break;

        case 3:
            getEditProfile();
            getMenu();
            break;

        case 4:
			exitApp();
			System.out.println("Thank You for using this ATM, bye.");
            break;

        default:
            System.out.println("\n" + "Invalid Choice." + "\n");
            getProfile();
        }
    }

	public void getEditProfile() {
        System.out.println("Edit Profile: ");
        System.out.println(" Type 1 - Phone Number");
        System.out.println(" Type 2 - Address");
        System.out.println(" Type 3 - Email address");
		System.out.println(" Type 4 - Back");
        System.out.println(" Type 5 - Exit");
        System.out.print("Choice: ");

        int selection = menuInput.nextInt();

		// avoid failing to wait for the input after calling Scanner.nextInt()
		menuInput.nextLine(); 

        switch (selection) {
        case 1:
		    System.out.println("Current phone number: " + profile.getPhoneNumber());
			System.out.print("New phone number:");
			String number = menuInput.nextLine();
			profile.setPhoneNumber(number);
			System.out.println("Phone number is modified.");
        	System.out.println("Current phone number: " + profile.getPhoneNumber());
            getMenu();
            break;

        case 2:
			System.out.println("Current address: " + profile.getAddress());
			System.out.print("New address:");
			String address = menuInput.nextLine();
			profile.setAddress(address);
			System.out.println("Address is modified.");
        	System.out.println("Current address: " + profile.getAddress());
            getMenu();
            break;

        case 3:
			System.out.println("Current email address: " + profile.getEmailAddress());
			System.out.print("New email address:");
			String emailAddress = menuInput.nextLine();
			profile.setEmailAddress(emailAddress);
			System.out.println("Email is modified.");
        	System.out.println("Current email address: " + profile.getEmailAddress());
			getMenu();
            break;

        case 4:
            getProfile();
            break;

		case 5:
			exitApp();
            System.out.println("Thank You for using this ATM, bye.");
            break;

        default:
            System.out.println("\n" + "Invalid Choice." + "\n");
            getEditProfile();
        }
    }

}






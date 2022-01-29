import java.io.IOException;

public class ATM extends OptionMenu {

	public static void main(String[] args) throws IOException {
		OptionMenu optionMenu = new OptionMenu();
		
		optionMenu.initDataPool();   // load customer data
		
		optionMenu.home();   		 // start the application process
	}

};


package com.techelevator.tenmo;

import com.techelevator.tenmo.models.AuthenticatedUser;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.UserCredentials;
import com.techelevator.tenmo.services.*;
import com.techelevator.view.ConsoleService;

public class TenmoApplicationProgram {

private static final String API_BASE_URL = "http://localhost:8080/";
    
    private static final String MENU_OPTION_EXIT = "Exit";
    private static final String LOGIN_MENU_OPTION_REGISTER = "Register";
	private static final String LOGIN_MENU_OPTION_LOGIN = "Login";
	private static final String[] LOGIN_MENU_OPTIONS = { LOGIN_MENU_OPTION_REGISTER, LOGIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	private static final String MAIN_MENU_OPTION_VIEW_BALANCE = "View your current balance";
	private static final String MAIN_MENU_OPTION_SEND_BUCKS = "Send TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS = "View your past transfers";
	private static final String MAIN_MENU_OPTION_REQUEST_BUCKS = "Request TE bucks";
	private static final String MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS = "View your pending requests";
	private static final String MAIN_MENU_OPTION_LOGIN = "Login as different user";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_VIEW_BALANCE, MAIN_MENU_OPTION_SEND_BUCKS, MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS, MAIN_MENU_OPTION_REQUEST_BUCKS, MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS, MAIN_MENU_OPTION_LOGIN, MENU_OPTION_EXIT };
	
    private AuthenticatedUser currentUser;
    private ConsoleService console;
    private AuthenticationService authenticationService;
	private AccountServices accountServices;
	private UserServices userServices;
	private TransferServices transferServices;


    public static void main(String[] args) {
    	TenmoApplicationProgram app = new TenmoApplicationProgram(new ConsoleService(System.in, System.out), new AuthenticationService(API_BASE_URL));
    	app.run();
    }

    public TenmoApplicationProgram(ConsoleService console, AuthenticationService authenticationService) {
		this.console = console;
		this.authenticationService = authenticationService;
		accountServices = new AccountServices(API_BASE_URL);
		userServices = new UserServices(API_BASE_URL);
		transferServices = new TransferServices(API_BASE_URL);
	}

	public void run() {
		System.out.println("*********************");
		System.out.println("* Welcome to TEnmo! *");
		System.out.println("*********************");
		
		registerAndLogin();
		mainMenu();
	}

	private void mainMenu() {
		while(true) {
			String choice = (String)console.getChoiceFromOptions(MAIN_MENU_OPTIONS);
			if(MAIN_MENU_OPTION_VIEW_BALANCE.equals(choice)) {
				viewCurrentBalance();
			} else if(MAIN_MENU_OPTION_VIEW_PAST_TRANSFERS.equals(choice)) {
				viewTransferHistory();
			} else if(MAIN_MENU_OPTION_VIEW_PENDING_REQUESTS.equals(choice)) {
				viewPendingRequests();
			} else if(MAIN_MENU_OPTION_SEND_BUCKS.equals(choice)) {
				sendBucks();
			} else if(MAIN_MENU_OPTION_REQUEST_BUCKS.equals(choice)) {
				requestBucks();
			} else if(MAIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else {
				// the only other option on the main menu is to exit
				exitProgram();
			}
		}
	}

	private void viewCurrentBalance() {
		// TODO - Put code for this process here
		console.printGetBalance(accountServices.getBalance(currentUser.getUser().getUsername()));
	}

	private void viewTransferHistory() {
		// TODO - Put code for this process here
		Integer currentAccountId = accountServices.getAccountByUserID(currentUser.getUser().getId()).getAccountId();
		Transfer[] transfers = transferServices.getTransfersFromAccount(currentAccountId);
		Integer transferId;

		console.printTransferHeader();

		for (Transfer transfer : transfers) {
			if (transfer.getAccountTo() == currentAccountId) {
				Integer otherUserId = accountServices.getUserIdFromAccountId(transfer.getAccountFrom());
				String otherUsername = userServices.getUsernameFromUserId(otherUserId);
				console.printReceivedTransfer(transfer, otherUsername);
			}

			else if (transfer.getAccountFrom() == currentAccountId) {
				Integer otherUserId = accountServices.getUserIdFromAccountId(transfer.getAccountTo());
				String otherUsername = userServices.getUsernameFromUserId(otherUserId);
				console.printSentTransfer(transfer, otherUsername);
			}
		}

		transferId = console.printSelectTransactionID();
		for (Transfer transfer : transfers) {
			if (transferId == transfer.getTransferId()) {
				String fromUsername = userServices.getUsernameFromUserId(transfer.getAccountFrom());
				String toUsername = userServices.getUsernameFromUserId(transfer.getAccountTo());
				String transferStatusText =  transferServices.getTransferStatusText(transfer.getTransferStatusId());
				String transferTypeText = transferServices.getTransferTypeText(transfer.getTransferTypeId());
				console.printTransferDetails(transfer, fromUsername, toUsername, transferTypeText, transferStatusText);
			} else if (transferId == 0) {
				break;
			} else {
				console.printInvalidTransferId();
				break;
			}
		}



	}// End of Method

	private void viewPendingRequests() {
		// TODO - Put code for this process here
		
	}

	private void sendBucks() {
		// TODO - Put code for this process here
		console.printTransferMenu(userServices.getUsers());
		Integer userId = console.getUserInputInteger("Enter ID of user you are sending to (0 to cancel)");
		Double amount = console.getAmountFromUser("Enter amount");

		if (userId == 0) {
			console.printBackToMainMenu();
		}

		if (accountServices.withdrawal(currentUser,amount)) {
			accountServices.deposit(userId, amount);
			transferServices.createSendTransfer(currentUser.getUser().getId(), userId, amount);
		}
		else {
			console.printInsufficientFunds();
		}
	}

	private void requestBucks() {
		// TODO - Put code for this process here
		
	}
	
	private void exitProgram() {
		System.exit(0);
	}
	/***********************************************************************************
	 * The code below works and should NOT need to be changed
	 * 
	 * It contains methods you may use in your processing
	 * 
	 * Consider reviewing it to at least understand it's general functionality
	 *     (what it returns, receives and does) in case your need it.
	 * 
	 * Remember: DRY - Don't Repeat Yourself 
	 *                (Don't write new code if functionality already exists)
	 ************************************************************************************/
	private void registerAndLogin() {
		while(!isAuthenticated()) {
			String choice = (String)console.getChoiceFromOptions(LOGIN_MENU_OPTIONS);
			if (LOGIN_MENU_OPTION_LOGIN.equals(choice)) {
				login();
			} else if (LOGIN_MENU_OPTION_REGISTER.equals(choice)) {
				register();
			} else {
				// the only other option on the login menu is to exit
				exitProgram();
			}
		}
	}

	private boolean isAuthenticated() {
		return currentUser != null;
	}

	private void register() {
		System.out.println("Please register a new user account");
		boolean isRegistered = false;
        while (!isRegistered) //will keep looping until user is registered
        {
            UserCredentials credentials = collectUserCredentials();
            try {
            	authenticationService.register(credentials);
            	isRegistered = true;
            	System.out.println("Registration successful. You can now login.");
            } catch(AuthenticationServiceException e) {
            	System.out.println("REGISTRATION ERROR: "+e.getMessage());
				System.out.println("Please attempt to register again.");
            }
        }
	}

	private void login() {
		System.out.println("Please log in");
		currentUser = null;
		while (currentUser == null) //will keep looping until user is logged in
		{
			UserCredentials credentials = collectUserCredentials();
		    try {
				currentUser = authenticationService.login(credentials);
			} catch (AuthenticationServiceException e) {
				System.out.println("LOGIN ERROR: "+e.getMessage());
				System.out.println("Please attempt to login again.");
			}
		}
	}
	
	private UserCredentials collectUserCredentials() {
		String username = console.getUserInput("Username");
		String password = console.getUserInput("Password");
		return new UserCredentials(username, password);
	}
}

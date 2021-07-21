package com.techelevator.view;


import com.techelevator.tenmo.models.Account;
import com.techelevator.tenmo.models.Transfer;
import com.techelevator.tenmo.models.User;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class ConsoleService {

	private PrintWriter out;
	private Scanner in;

	public ConsoleService(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output, true);
		this.in = new Scanner(input);
	}

	public Object getChoiceFromOptions(Object[] options) {
		Object choice = null;
		while (choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		out.println();
		return choice;
	}

	private Object getChoiceFromUserInput(Object[] options) {
		Object choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if (selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch (NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if (choice == null) {
			out.println(System.lineSeparator() + "*** " + userInput + " is not a valid option ***" + System.lineSeparator());
		}
		return choice;
	}

	private void displayMenuOptions(Object[] options) {
		out.println();
		for (int i = 0; i < options.length; i++) {
			int optionNum = i + 1;
			out.println(optionNum + ") " + options[i]);
		}
		out.print(System.lineSeparator() + "Please choose an option >>> ");
		out.flush();
	}

	public String getUserInput(String prompt) {
		out.print(prompt+": ");
		out.flush();
		return in.nextLine();
	}

	public Integer getUserInputInteger(String prompt) {
		Integer result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Integer.parseInt(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}

	public void printGetBalance(Double balance) {
		out.println("Your current account balance is: $" + balance);
	}

	public void printTransferMenu(User[] users) {
		String spaces = "          ";
		out.println("--------------------------------------------------");
		out.println("Users");
		out.println("ID" + spaces + "Name");
		out.println("--------------------------------------------------");

		for (User user : users) {
			out.println(user.getId() + spaces + user.getUsername());
		}

		out.println("-------------------\n");
	}

	public Double getAmountFromUser(String prompt) {
		Double result = null;
		do {
			out.print(prompt+": ");
			out.flush();
			String userInput = in.nextLine();
			try {
				result = Double.parseDouble(userInput);
			} catch(NumberFormatException e) {
				out.println(System.lineSeparator() + "*** " + userInput + " is not valid ***" + System.lineSeparator());
			}
		} while(result == null);
		return result;
	}

	public void printInsufficientFunds() {
		out.println("******************************************************************************");
		out.println("Unable to complete transfer. Transfer amount greater than account balance.");
		out.println("******************************************************************************");
	}

	public void printInvalidTransferId() {
		out.println("******************************************************************************");
		out.println("Invalid Transfer ID. Unable to display Transfer Details.");
		out.println("******************************************************************************");
	}

	public void printBackToMainMenu() {
		out.println("Navigating back to main menu.");
	}

	public void printTransferHeader() {
		String spaces = "          ";
		out.println("--------------------------------------------------");
		out.println("Transfers");
		out.println("ID" + spaces + "From/To" + spaces + "  " + "Amount");
		out.println("--------------------------------------------------");
	}

	public void printSentTransfer(Transfer transfer, String username) {
		String spaces = "          ";
		out.println(transfer.getTransferId() + spaces + "To:   " + username + spaces + "$" + transfer.getAmount());
	}

	public void printReceivedTransfer(Transfer transfer, String username) {
		String spaces = "          ";

		out.println(transfer.getTransferId() + spaces + "From: " + username + spaces + "$" + transfer.getAmount());
	}

	public Integer printSelectTransactionID() {
		out.println("---------");
		return getUserInputInteger("Please enter transfer ID to view details (0 to cancel)");
		}

	public void printTransferDetails(Transfer transfer, String fromUsername, String toUsername, String typeText, String statusText) {
		String spaces = "          ";
		out.println("--------------------------------------------------");
		out.println("Transfers Details");
		out.println("--------------------------------------------------");

		out.println("Id:      " + transfer.getTransferId());
		out.println("From:    " + fromUsername);
		out.println("To:      " + toUsername);
		out.println("Type:    " + typeText);
		out.println("Status:  " + statusText);
		out.println("Amount: $" + transfer.getAmount());



	}


}// End of Class

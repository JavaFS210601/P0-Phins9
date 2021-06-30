package com.revature.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.daos.AccountDao;

public class Menu {
	
	AccountDao ed = new AccountDao();
	
	public void display() {
		
		boolean displayMenu = true;
		Scanner scan = new Scanner(System.in);
		final Logger log = LogManager.getLogger(Menu.class);
		
		System.out.println("Welcome to your local Bank's Account Management System");
		System.out.println("======================================================");
		
		while(displayMenu) {
			
			System.out.println("-----------------");
			System.out.println("Choose an option!");
			System.out.println("-----------------");
			
			System.out.println("Accounts   -> show all accounts");
			System.out.println("Add        -> Add an account ");
			System.out.println("Delete     -> Delete an account");
			System.out.println("Withdraw   -> Withdraw money from an account");
			System.out.println("Deposit    -> Deposit money into an account");
			System.out.println("ChangeRole -> Change the role of an account");
			System.out.println("Exit       -> Exit the application.");
			System.out.println();
			System.out.println("What would you like to do?");
			System.out.println();
			
			String input = scan.nextLine().toLowerCase();
			
			switch(input) {
			
			case "accounts": {
				System.out.println("Gathering all Accounts!");
				
				List<Account> accounts = ed.getAccounts();
				for(Account e : accounts) {
					System.out.println(e);
				}
				System.out.println();
				
				break;
			}
			case "add": {
				
				System.out.println("Enter new account First Name: ");
				String f_name = scan.nextLine();
				
				System.out.println("Enter new account Last Name: ");
				String l_name = scan.nextLine();
				
				System.out.println("Enter the starting balance of the new account: ");
				int balance = scan.nextInt();
				
				System.out.println("Enter the role ID of the new account: ");
				int roleId = scan.nextInt();
				
				scan.nextLine();
				
				Account newAccount = new Account(balance, f_name, l_name, "xxx", balance, roleId);
				
				ed.addAccount(newAccount);
				log.info("add method ran and added a user");
				
				break;
				
			}
			case "withdraw": {
				System.out.println("These are the accounts in the bank: ");
				
				List<Account> employees = ed.getAccounts();
				
				for(Account e : employees) {
					System.out.println(e);
				}

				System.out.println("--------------------------");

				System.out.println("Enter the ID of the account you are withdrawing from:");
				int idInput = scan.nextInt();
				scan.nextLine();
				

				System.out.println("Enter how much you are withdrawing: ");
				int withdraw = scan.nextInt();
				scan.nextLine();
				
				withdraw = withdraw * (-1);
				ed.changeBalance(idInput, withdraw);
				
				log.info("withdraw method ran and withdrew money");
				
				break;
				
			}
			case "deposit": {
				System.out.println("These are the accounts in the bank: ");
				
				List<Account> employees = ed.getAccounts();
				
				for(Account e : employees) {
					System.out.println(e);
				}

				System.out.println("--------------------------");

				System.out.println("Enter the ID of the account you are depositting to:");
				int idInput = scan.nextInt();
				scan.nextLine();
				

				System.out.println("Enter how much you are depositting: ");
				int deposit = scan.nextInt();
				scan.nextLine();
				
				ed.changeBalance(idInput, deposit);
				log.info("deposit method ran and depositted money");
				
				break;
				
			}			
			case "changerole": {
				System.out.println("These are the accounts in the bank: ");
				
				List<Account> employees = ed.getAccounts();
				
				for(Account e : employees) {
					System.out.println(e);
				}

				System.out.println("--------------------------");

				System.out.println("Enter the ID of the account you changing the role for:");
				int idInput = scan.nextInt();
				scan.nextLine();
				

				System.out.println("Enter the new role for the account: ");
				int roles = scan.nextInt();
				scan.nextLine();
				
				ed.changeRole(idInput, roles);
				
				break;
				
			}
			case "delete": {
				System.out.println("These are the accounts in the bank: ");
				
				List<Account> accounts = ed.getAccounts();
				
				for(Account e : accounts) {
					System.out.println(e);
				}

				System.out.println("--------------------------");

				System.out.println("Enter the ID of the account you are deleting:");
				int idInput = scan.nextInt();
				scan.nextLine();
				
				ed.removeAccount(idInput);
				log.info("delete method ran and deleted a user");
			
				break;
			}
			case "exit": {
				System.out.println("Exting, bye!");
				displayMenu = false;
				break;
			}
			default: {
				System.out.println("Didn't catch that... try again");
				break;
			}
			
			}
			
		}
		
	}

}

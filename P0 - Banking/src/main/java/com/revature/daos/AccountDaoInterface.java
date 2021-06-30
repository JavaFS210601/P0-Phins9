package com.revature.daos;

import java.util.List;

import com.revature.models.Account;

public interface AccountDaoInterface {
	
	public List<Account> getAccounts();

	public void addAccount(Account acc);

	public void changeBalance(int accId, int balance);

	public void removeAccount(int accId);

	public void changeRole(int accId, int roles);
}

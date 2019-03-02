package com.sa.springmvc.sabankApp.services;

import java.util.List;

import com.sa.springmvc.sabankApp.model.Account;

public interface AccountService {
	public boolean saveAccount(Account account);
	public List<Account> getAccounts();
	public Account getAccount(Integer accountNo);
	public boolean deleteAccount(int accountNo);

}

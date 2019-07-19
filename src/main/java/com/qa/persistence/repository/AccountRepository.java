package com.qa.persistence.repository;

import java.util.List;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;

public interface AccountRepository {
	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failure ";

	String getAllAccounts();

	String createAccount(String account);

	String deleteAccount(int accountId) throws AccountNotFoundException;

	String updateAccount(int accountId, String account) throws AccountNotFoundException;

	List<Account> findAccountsByFirstName(String firstName);

}

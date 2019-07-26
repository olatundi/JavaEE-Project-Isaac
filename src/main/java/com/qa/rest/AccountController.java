package com.qa.rest;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.service.AccountService;

@Path("/account")
public class AccountController {

	@Inject
	private AccountService service;

	@GET
	@Path("/getAll")
	public String getAllAccounts() {
		return this.service.getAllAccounts();
	}

	@POST
	@Path("/createAccount")
	public String createAccount(String account) {
		return this.service.createAccount(account);
	}

	@DELETE
	@Path("/delete/{accountId}")
	public String deleteAccount(@PathParam("accountId") int accountId) {
		return this.service.deleteAccount(accountId);
	}

	@POST
	@Path("/update/{accountId}")
	public String updateAccount(@PathParam("accountId") int accountId, String account) {
		return this.service.updateAccount(accountId, account);
	}
	
	@Path("/login")
	@POST
	public String login(String account) {
		return this.service.login(account);
	}
	
	@Path("/findAcc/{accountId}")
	@GET
	public String findAccount(@PathParam("accountId") int accountId) {
		return this.service.findAccountByUserID(accountId);
	}
}


package com.qa.persistence.repository;

import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.exceptions.AccountNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Board;
import com.qa.persistence.domain.Task;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
public class AccountDBRepository implements AccountRepository {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	@Inject
	private JSONUtil util;
	
	public String getAllAccounts() {
		TypedQuery<Account> query = em.createQuery("SELECT A FROM Account A", Account.class);
		return this.util.getJSONForObject(query.getResultList());
	}

	@Transactional(value = TxType.REQUIRED)
	public String createAccount(String account) {
		Account aAccount = util.getObjectForJSON(account, Account.class);
		this.em.persist(aAccount);
		return SUCCESS;
	}

	public Account findAccount(Long id) {
        return this.em.find(Account.class, id);
    }

	@Transactional(value = TxType.REQUIRED)
	public String deleteAccount(int accountID) {
		TypedQuery<Task> query = this.em.createQuery("SELECT t FROM Task t WHERE t.board.account.id = :id",
				Task.class);
		query.setParameter("id", accountID);
		
		for(Task t: query.getResultList()) {
			Task tasRem = this.em.find(Task.class, t.getId());
			this.em.remove(tasRem);
		}
		
		TypedQuery<Board> query1 = this.em.createQuery("SELECT b FROM Board b WHERE b.account.id = :id",
				Board.class);
		query1.setParameter("id", accountID);
		
		for(Board b: query1.getResultList()) {
			Board boaRem = this.em.find(Board.class, b.getId());
			this.em.remove(boaRem);
		}
	
		Account toRem = em.find(Account.class, accountID);
		this.em.remove(toRem);
		return SUCCESS;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String updateAccount(int accountID, String account) {
		Account aAccount = util.getObjectForJSON(account, Account.class);
		Account existing = em.find(Account.class, accountID);
		if (existing == null) {
			throw new AccountNotFoundException();
		}
		existing.setUsername(aAccount.getUsername());
		existing.setPassword(aAccount.getPassword());
		existing.setFirstName(aAccount.getFirstName());
		existing.setLastName(aAccount.getLastName());
		this.em.persist(existing);	
		return SUCCESS;
	}

	public String findAccountByUserID(int userID) {
		TypedQuery<Account> query = this.em.createQuery("SELECT a FROM Account a WHERE a.id = :userID",
				Account.class);
		query.setParameter("userID", userID);
		
		return this.util.getJSONForObject(query.getResultList());
	}
	
	public String login(String account) {
		Account anAcc = this.util.getObjectForJSON(account, Account.class);
		String username = anAcc.getUsername();
		String password = anAcc.getPassword();
		TypedQuery<Account> query = this.em.createQuery("Select a From Account a WHERE a.username='"+username+"' AND a.password ='"+password+"'" ,
				Account.class);
		List<Account> result = query.getResultList();
		return this.util.getJSONForObject(result);
	}
}
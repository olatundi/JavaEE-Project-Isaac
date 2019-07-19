package com.qa.persistence.repository;

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
import com.qa.util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
@Default
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
		existing.setId(aAccount.getId());
		existing.setUsername(aAccount.getUsername());
		existing.setPassword(aAccount.getPassword());
		existing.setFirstName(aAccount.getFirstName());
		existing.setLastName(aAccount.getLastName());
		this.em.persist(existing);	
		return SUCCESS;
	}


	public List<Account> findAccountsByUsername(String userName) {
		TypedQuery<Account> query = this.em.createQuery("SELECT a FROM Account a WHERE a.userName = :userName",
				Account.class);
		query.setParameter("userName", userName);
		return query.getResultList();
	}
}
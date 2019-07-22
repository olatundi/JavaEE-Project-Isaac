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
import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Board;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
@Default
public class TaskDBRepository implements TaskRepository {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	@Inject
	private JSONUtil util;
	
	
	@Inject
	private AccountRepository accRepo;
	
	public String getAllTasks() {
		TypedQuery<Board> query = em.createQuery("SELECT T FROM Task T", Board.class);
		return this.util.getJSONForObject(query.getResultList());
	}

	
	@Transactional(value = TxType.REQUIRED)
	public String createTask (int accountID, String task ) {
		Board aTask = util.getObjectForJSON(task, Board.class);
		Account foundAcc = this.accRepo.findAccountByUserID(accountID).get(0);
//		this.em.find(Account.class, foundAcc.ge)
		aTask.setAccount(foundAcc);
		this.em.persist(aTask);
		return SUCCESS;
	}

	public Board findTask(Integer id) {
        return this.em.find(Board.class, id);
    }

	@Transactional(value = TxType.REQUIRED)
	public String deleteTask(int taskId) {
		Board toRem = this.em.find(Board.class, taskId);
		this.em.remove(toRem);
		return SUCCESS;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String updateTask(int id, String task) {
		Board aTask = this.util.getObjectForJSON(task, Board.class);
		Board existing = this.em.find(Board.class, id);
		if (existing == null) {
			throw new BoardNotFoundException();
		}
		existing.setAccount(aTask.getAccount());
		existing.setDescription(aTask.getDescription());
		existing.setPriority(aTask.getPriority());
		this.em.persist(existing);	
		return SUCCESS;
	}


	public List<Board> findTaskByAccountID(int userID) {
		TypedQuery<Board> query = this.em.createQuery("SELECT a FROM Account a WHERE a.account.id = :id",
				Board.class);
		query.setParameter("id", userID);
		return query.getResultList();
	}


	
}
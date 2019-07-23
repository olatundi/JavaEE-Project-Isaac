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
import com.qa.exceptions.TaskNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Board;
import com.qa.persistence.domain.Task;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
@Default
public class TaskDBRepository implements TaskRepository {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	@Inject
	private JSONUtil util;
	
	
	@Inject
	private BoardRepository boardRepo;
	
	public String getAllTasks() {
		TypedQuery<Task> query = em.createQuery("SELECT T FROM Task T", Task.class);
		return this.util.getJSONForObject(query.getResultList());
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String createTask (int boardID, String task ) {
		Task aTask = util.getObjectForJSON(task, Task.class);
		Board foundBoard = this.boardRepo.findBoardByBoardID(boardID).get(0);
//		this.em.find(Account.class, foundAcc.ge)
		aTask.setBoard(foundBoard);
		this.em.persist(aTask);
		return SUCCESS;
	}

	public Task findTask(Integer id) {
        return this.em.find(Task.class, id);
    }

	@Transactional(value = TxType.REQUIRED)
	public String deleteTask(int taskId) {
		Task toRem = this.em.find(Task.class, taskId);
		this.em.remove(toRem);
		return SUCCESS;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String updateTask(int id, String task) {
		Task aTask = this.util.getObjectForJSON(task, Task.class);
		Task existing = this.em.find(Task.class, id);
		if (existing == null) {
			throw new TaskNotFoundException();
		}
		existing.setBoard(aTask.getBoard());
		existing.setDescription(aTask.getDescription());
		existing.setPriority(aTask.getPriority());
		this.em.persist(existing);	
		return SUCCESS;
	}


	public List<Task> findTaskByAccountID(int userID) {
		TypedQuery<Task> query = this.em.createQuery("SELECT a FROM Account a WHERE a.account.id = :id",
				Task.class);
		query.setParameter("id", userID);
		return query.getResultList();
	}


	
}
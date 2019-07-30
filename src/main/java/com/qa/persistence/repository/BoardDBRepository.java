package com.qa.persistence.repository;

import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Board;
import com.qa.persistence.domain.Task;
import com.qa.util.JSONUtil;

@Transactional(value = TxType.SUPPORTS)
@Default
public class BoardDBRepository implements BoardRepository {
	
	@PersistenceContext(unitName = "primary")
    private EntityManager em;
	@Inject
	private JSONUtil util;
	
	private AccountRepository accRepo;
	
	public String getAllBoards() {
		TypedQuery<Board> query = em.createQuery("SELECT B FROM Board B", Board.class);
		return this.util.getJSONForObject(query.getResultList());
	}

	@Transactional(value = TxType.REQUIRED)
	public String createBoard (int accountID, String board ) {
		Board aBoard = this.util.getObjectForJSON(board, Board.class);
//		Account foundAcc = this.accRepo.findAccountByUserID(accountID).get(0);
		Account foundAcc2 = this.em.find(Account.class, accountID);
		if (foundAcc2 == null) {
			throw new BoardNotFoundException();
		}
		
		aBoard.setAccount(foundAcc2);
		this.em.persist(aBoard);
		return SUCCESS;
	}

	public Board findBoard(Integer id) {
        return this.em.find(Board.class, id);
    }

	@Transactional(value = TxType.REQUIRED)
	public String deleteBoard(int boardId) {
		
		TypedQuery<Task> query = this.em.createQuery("SELECT t FROM Task t WHERE t.board.id = :id",
				Task.class);
		query.setParameter("id", boardId);
		
		for(Task t: query.getResultList()) {
			Task tasRem = this.em.find(Task.class, t.getId());
			this.em.remove(tasRem);
		}
		
		Board toRem = this.em.find(Board.class, boardId);
		this.em.remove(toRem);
		return SUCCESS;
	}
	
	@Transactional(value = TxType.REQUIRED)
	public String updateBoard(int id, String board) {
		Board aBoard = this.util.getObjectForJSON(board, Board.class);
		Board existing = this.em.find(Board.class, id);
		if (existing == null) {
			throw new BoardNotFoundException();
		}
		existing.setName(aBoard.getName());
		this.em.persist(existing);	
		return SUCCESS;
	}

	public String findBoardByAccID(int accountID) {
		TypedQuery<Board> query = this.em.createQuery("SELECT b FROM Board b WHERE b.account.id = :id",
				Board.class);
		query.setParameter("id", accountID);
		return this.util.getJSONForObject(query.getResultList());
	}
	
}
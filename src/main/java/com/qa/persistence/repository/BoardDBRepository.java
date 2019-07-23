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
		Board aBoard = util.getObjectForJSON(board, Board.class);
		Account foundAcc = this.accRepo.findAccountByUserID(accountID).get(0);
		Account foundAcc2 = this.em.find(Account.class, accountID);
		if (foundAcc == null) {
			throw new BoardNotFoundException();
		}
		else if(foundAcc2 ==null) {
			return"2 is wrong";
		}
		aBoard.setAccount(foundAcc);
		this.em.persist(aBoard);
		return SUCCESS;
	}

	public Board findBoard(Integer id) {
        return this.em.find(Board.class, id);
    }

	@Transactional(value = TxType.REQUIRED)
	public String deleteBoard(int boardId) {
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
		existing.setAccount(aBoard.getAccount());
		existing.setName(aBoard.getName());
		this.em.persist(existing);	
		return SUCCESS;
	}

	public List<Board> findBoardByBoardID(int boardID) {
		TypedQuery<Board> query = this.em.createQuery("SELECT b FROM Board b WHERE b.id = :id",
				Board.class);
		query.setParameter("id", boardID);
		return query.getResultList();
	}
	
}
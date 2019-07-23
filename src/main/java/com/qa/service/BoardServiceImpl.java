package com.qa.service;

import java.util.List;

import javax.inject.Inject;

import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Board;
import com.qa.persistence.repository.BoardRepository;
import com.qa.persistence.repository.TaskRepository;

public class BoardServiceImpl implements BoardService{

	
	@Inject
	private BoardRepository repo;
	@Override
	public String getAllBoards() {
		// TODO Auto-generated method stub
		return this.repo.getAllBoards();
	}

	@Override
	public String createBoard(int accountID, String board) {
		// TODO Auto-generated method stub
		return this.repo.createBoard(accountID, board);
	}

	@Override
	public String deleteBoard(int boardId) throws BoardNotFoundException {
		// TODO Auto-generated method stub
		return this.repo.deleteBoard(boardId);
	}

	@Override
	public String updateBoard(int boardId, String board) throws BoardNotFoundException {
		// TODO Auto-generated method stub
		return this.repo.updateBoard(boardId, board);
	}

	@Override
	public List<Board> findBoardByBoardID(int boardID) {
		// TODO Auto-generated method stub
		return this.repo.findBoardByBoardID(boardID);
	}

	
	
}

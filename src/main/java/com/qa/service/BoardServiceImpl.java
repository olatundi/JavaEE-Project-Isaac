package com.qa.service;

import java.util.List;

import javax.inject.Inject;

import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Board;
import com.qa.persistence.repository.BoardRepository;



public class BoardServiceImpl implements BoardService{

	
	@Inject
	private BoardRepository boardRepo;
	@Override
	public String getAllBoards() {
		// TODO Auto-generated method stub
		return this.boardRepo.getAllBoards();
	}

	@Override
	public String createBoard(int accountID, String board) {
		// TODO Auto-generated method stub
		return this.boardRepo.createBoard(accountID, board);
	}

	@Override
	public String deleteBoard(int boardId) throws BoardNotFoundException {
		// TODO Auto-generated method stub
		return this.boardRepo.deleteBoard(boardId);
	}

	@Override
	public String updateBoard(int boardId, String board) throws BoardNotFoundException {
		// TODO Auto-generated method stub
		return this.boardRepo.updateBoard(boardId, board);
	}

	@Override
	public List<Board> findBoardByBoardID(int boardID) {
		// TODO Auto-generated method stub
		return this.boardRepo.findBoardByBoardID(boardID);
	}

	
	
}

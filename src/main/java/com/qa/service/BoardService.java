package com.qa.service;

import java.util.List;

import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Board;

public interface BoardService {
	String getAllBoards();

	String createBoard(int accountID, String board);

	String deleteBoard(int boardId) throws BoardNotFoundException;

	String updateBoard(int boardId, String board) throws BoardNotFoundException;

	List<Board> findBoardByBoardID(int boardID);;

}
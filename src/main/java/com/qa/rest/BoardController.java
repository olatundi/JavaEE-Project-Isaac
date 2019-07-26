package com.qa.rest;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.service.BoardService;

@Path("/board")
public class BoardController {

	@Inject
	private BoardService service;

	@GET
	@Path("/getAll")
	public String getAllBoards() {
		return this.service.getAllBoards();
	}

	@POST
	@Path("/create/{accountId}")
	public String createBoard(@PathParam("accountId") int accountId, String board) {
		return this.service.createBoard(accountId, board);
	}

	@DELETE
	@Path("/delete/{boardId}")
	public String deleteBoard(@PathParam("boardId") int boardId) {
		return this.service.deleteBoard(boardId);
	}

	@POST
	@Path("/update/{boardId}")
	public String updateBoard(@PathParam("boardId") int boardId, String board) {
		return this.service.updateBoard(boardId, board);
	}
	
	@Path("/findBoard/{accountId}")
	@GET
	public String findBoard(@PathParam("accountId") int accountId) {
		return this.service.findBoardByAccID(accountId);
	}
}


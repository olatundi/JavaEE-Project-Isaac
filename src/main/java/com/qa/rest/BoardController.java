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
	@Path("/createBoard/{id}")
	public String createBoard(@PathParam("id") int accountId, String board) {
		return this.service.createBoard(accountId, board);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteBoard(@PathParam("id") int boardId) {
		return this.service.deleteBoard(boardId);
	}

	@POST
	@Path("/update/{id}")
	public String updateBoard(@PathParam("id") int boardId, String board) {
		return this.service.updateBoard(boardId, board);
	}
}


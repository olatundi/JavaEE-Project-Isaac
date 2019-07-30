package com.qa.rest;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.service.TaskService;

@Path("/task")
public class TaskController {

	@Inject
	private TaskService service;

	@GET
	@Path("/getAll")
	public String getAllTasks() {
		return this.service.getAllTasks();
	}

	@POST
	@Path("/create/{boardId}")
	public String createTask(@PathParam("boardId") int boardId, String task) {
		return this.service.createTask(boardId, task);
	}

	@DELETE
	@Path("/delete/{taskId}")
	public String deleteTask(@PathParam("taskId") int taskId) {
		return this.service.deleteTask(taskId);
	}

	@POST
	@Path("/update/{taskId}")
	public String updateTask(@PathParam("taskId") int taskId, String task) {
		return this.service.updateTask(taskId, task);
	}
	
	@Path("/findTask/{boardId}")
	@GET
	public String findTask(@PathParam("boardId") int boardId) {
		return this.service.findTasksByBoardID(boardId);
	}
}


package com.qa.rest;


import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.qa.service.AccountService;
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
	@Path("/createTask/{id}")
	public String createTask(@PathParam("id") int accountId, String task) {
		return this.service.createTask(accountId, task);
	}

	@DELETE
	@Path("/delete/{id}")
	public String deleteTask(@PathParam("id") int taskId) {
		return this.service.deleteTask(taskId);
	}

	@POST
	@Path("/update/{id}")
	public String updateTask(@PathParam("id") int taskId, String task) {
		return this.service.updateTask(taskId, task);
	}
}


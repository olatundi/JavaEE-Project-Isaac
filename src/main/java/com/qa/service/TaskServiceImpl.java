package com.qa.service;

import java.util.List;

import javax.inject.Inject;

import com.qa.exceptions.TaskNotFoundException;
import com.qa.persistence.domain.Task;
import com.qa.persistence.repository.TaskRepository;

public class TaskServiceImpl implements TaskService{

	
	@Inject
	private TaskRepository repo;
	@Override
	public String getAllTasks() {
		// TODO Auto-generated method stub
		return this.repo.getAllTasks();
	}

	@Override
	public String createTask(int accountID, String task) {
		// TODO Auto-generated method stub
		return this.repo.createTask(accountID, task);
	}

	@Override
	public String deleteTask(int taskId) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		return this.repo.deleteTask(taskId);
	}

	@Override
	public String updateTask(int taskId, String task) throws TaskNotFoundException {
		// TODO Auto-generated method stub
		return this.repo.updateTask(taskId, task);
	}

	@Override
	public List<Task> findTasksByAccountID(int userId) {
		// TODO Auto-generated method stub
		return this.repo.findTaskByAccountID(userId);
	}

	
	
}

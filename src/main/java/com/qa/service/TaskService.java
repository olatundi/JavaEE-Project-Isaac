package com.qa.service;

import java.util.List;

import com.qa.exceptions.TaskNotFoundException;
import com.qa.persistence.domain.Task;

public interface TaskService {
	String getAllTasks();

	String createTask(String task);

	String deleteTask(int taskId) throws TaskNotFoundException;

	String updateTask(int taskId, String task) throws TaskNotFoundException;

	List<Task> findTasksByAccountID(int userID);

}
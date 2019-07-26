package com.qa.persistence.repository;

import com.qa.exceptions.TaskNotFoundException;

public interface TaskRepository {
	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failure ";

	String getAllTasks();

	String createTask(int accountID, String task);

	String deleteTask(int taskId) throws TaskNotFoundException;

	String updateTask(int taskId, String task) throws TaskNotFoundException;

	String findTaskByAccountID(int userID);

}

package com.qa.persistence.repository;

import java.util.List;

import com.qa.exceptions.TaskNotFoundException;
import com.qa.persistence.domain.Task;

public interface TaskRepository {
	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failure ";

	String getAllTasks();

	String createTask(int accountID, String task);

	String deleteTask(int taskId) throws TaskNotFoundException;

	String updateTask(int taskId, String task) throws TaskNotFoundException;

	List<Task> findTaskByAccountID(int userID);

}

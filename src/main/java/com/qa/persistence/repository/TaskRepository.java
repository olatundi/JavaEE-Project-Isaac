package com.qa.persistence.repository;

import java.util.List;

import com.qa.exceptions.TaskNotFoundException;
import com.qa.persistence.domain.Task;

public interface TaskRepository {
	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failure ";

	String getAllTasks();

	String createTask(String task);

	String deleteTask(int taskId) throws TaskNotFoundException;

	String updateTask(int taskId, String account) throws TaskNotFoundException;

	List<Task> findTaskByUserID(int userID);

}

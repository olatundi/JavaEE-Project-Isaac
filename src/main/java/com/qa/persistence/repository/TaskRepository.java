package com.qa.persistence.repository;

import java.util.List;

import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Board;

public interface TaskRepository {
	final String SUCCESS = "Operation succeeded";
	final String FAILURE = "Operation failure ";

	String getAllTasks();

	String createTask(int accountID, String task);

	String deleteTask(int taskId) throws BoardNotFoundException;

	String updateTask(int taskId, String task) throws BoardNotFoundException;

	List<Board> findTaskByAccountID(int userID);

}

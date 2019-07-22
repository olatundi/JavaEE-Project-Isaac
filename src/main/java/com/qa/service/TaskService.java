package com.qa.service;

import java.util.List;

import com.qa.exceptions.BoardNotFoundException;
import com.qa.persistence.domain.Board;

public interface TaskService {
	String getAllTasks();

	String createTask(int accountID, String task);

	String deleteTask(int taskId) throws BoardNotFoundException;

	String updateTask(int taskId, String task) throws BoardNotFoundException;

	List<Board> findTasksByAccountID(int userID);

}
package com.qa.DBTests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Task;
import com.qa.persistence.repository.TaskDBRepository;
import com.qa.util.JSONUtil;

public class TaskServiceTest {

	private TaskDBRepository tr;
	private final String Task_JSON_1 = "{\"id\":1,\"description\":\"Task 1\",\"priority\":\"Low\"}";
	private final JSONUtil JSON = new JSONUtil();
	private final Task Task_1 = new Task(1, "Task 1", "Low");

	@Before
	public void setup() {
		this.tr = new TaskDBRepository();
	}

	@Test
	public void jsonStringToBoardConversionTest() {
		assertEquals("Failed to convert to Board from JSON", this.Task_1,
				this.JSON.getObjectForJSON(this.Task_JSON_1, Task.class));
	}

	@Test
	public void boardConversionToJSONTest() {
		// testing JSONUtil
		assertEquals("Failed to convert to JSON", this.Task_JSON_1, this.JSON.getJSONForObject(this.Task_1));
	}

}

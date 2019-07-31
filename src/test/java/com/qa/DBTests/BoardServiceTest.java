package com.qa.DBTests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Board;
import com.qa.persistence.repository.BoardDBRepository;
import com.qa.util.JSONUtil;

public class BoardServiceTest {

	private BoardDBRepository br;
	private final String Board_JSON_1 = "{\"id\":1,\"name\":\"boardname1\"}";
	private final JSONUtil JSON = new JSONUtil();
	private final Board Board_1 = new Board(1, "boardname1");

	@Before
	public void setup() {
		this.br = new BoardDBRepository();
	}

	@Test
	public void jsonStringToBoardConversionTest() {
		assertEquals("Failed to convert to Board from JSON", this.Board_1,
				this.JSON.getObjectForJSON(this.Board_JSON_1, Board.class));
	}

	@Test
	public void boardConversionToJSONTest() {
		// testing JSONUtil
		assertEquals("Failed to convert to JSON", this.Board_JSON_1, this.JSON.getJSONForObject(this.Board_1));
	}

}

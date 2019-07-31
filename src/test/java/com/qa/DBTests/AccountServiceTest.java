package com.qa.DBTests;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import com.qa.persistence.domain.Account;
import com.qa.persistence.repository.AccountDBRepository;
import com.qa.util.JSONUtil;

public class AccountServiceTest {

	private AccountDBRepository amr;
	private final String ACCOUNT_JSON_1 = "{\"id\":1,\"username\":\"add3\",\"firstName\":\"SCOOT\",\"lastName\":\"PACINO\",\"password\":\"21\"}";
	private final JSONUtil JSON = new JSONUtil();
	private final Account ACCOUNT_1 = new Account(1, "add3", "SCOOT", "PACINO", "21");

	@Before
	public void setup() {
		this.amr = new AccountDBRepository();
	}

	@Test
	public void jsonStringToAccountConversionTest() {
		assertEquals("Failed to convert to Account from JSON", this.ACCOUNT_1,
				this.JSON.getObjectForJSON(this.ACCOUNT_JSON_1, Account.class));
	}

	@Test
	public void accountConversionToJSONTest() {
		// testing JSONUtil
		assertEquals("Failed to convert to JSON", this.ACCOUNT_JSON_1, this.JSON.getJSONForObject(this.ACCOUNT_1));
	}



}

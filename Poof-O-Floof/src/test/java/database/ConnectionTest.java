package database;

import org.junit.Test;

import util.ConnectionUtil;

public class ConnectionTest {

	// Smoke Test
	@Test
	public void connectionFactory_SmokeTest() {
		assert ConnectionUtil.getConnection() != null;
	}
}

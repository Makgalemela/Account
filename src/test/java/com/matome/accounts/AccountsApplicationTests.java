package com.matome.accounts;

import com.matome.accounts.requests.FileProcessorTest;
import com.matome.accounts.requests.NoResourceAccountTest;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
		FileProcessorTest.class,
		NoResourceAccountTest.class
})class AccountsApplicationTests {

	@Test
	void contextLoads() {
	}

}

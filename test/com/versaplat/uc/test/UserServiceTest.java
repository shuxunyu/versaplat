package com.versaplat.uc.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.versaplat.uc.entity.User;
import com.versaplat.uc.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"/mvc-context.xml"})
public class UserServiceTest {

	@Autowired
	private UserService userService;
	
	@Test
	public void testHasMatchUser() {
		boolean b1 = userService.hasMatchUser("admin", "123456");
		boolean b2 = userService.hasMatchUser("adm","abcd");
		assertTrue(b1);
		assertTrue(!b2);
	}

	@Test
	public void testFindUserByName() {
		User user = userService.findUserByName("admin");
		assertEquals(user.getUser_name(), "admin");
	}

	@Test
	public void testLoginSuccess() {
		userService.loginSuccess("1", "localhost", "20150829");
	}

}

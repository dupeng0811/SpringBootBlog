package com.study.springbootblog;

import com.study.springbootblog.entity.MUser;
import com.study.springbootblog.service.impl.MUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SpringBootBlogApplicationTests {

	@Autowired
	MUserServiceImpl _userService;
	@Test
	void contextLoads() {
	}

	@Test
	void testGetUser(){
		List<MUser> userList = _userService.list();
		userList.forEach(System.out::println);
	}
}

package com.study.springbootblog;

import com.study.springbootblog.mapper.MUserMapper;
import com.study.springbootblog.entity.MUser;
import com.study.springbootblog.service.IMPostService;
import com.study.springbootblog.service.IMUserService;
import com.study.springbootblog.service.impl.MCommentServiceImpl;
import com.study.springbootblog.service.impl.MUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;

@SpringBootTest
class SpringBootBlogApplicationTests {

	@Autowired
	MUserServiceImpl _userService;
	@Test
	void contextLoads() {
	}

	@Test
	void testGetUser(){
		System.out.println(_userService.list());
		System.out.println("test");
	}
}

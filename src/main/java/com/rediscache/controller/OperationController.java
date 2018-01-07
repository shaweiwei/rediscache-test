package com.rediscache.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rediscache.entity.User;
import com.rediscache.factory.UserFactory;
import com.rediscache.service.UserService;
import com.google.gson.Gson;



@RestController
@RequestMapping("/o")
public class OperationController {
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Autowired
	private UserService userService;
	
	Gson gson = new Gson();
	
	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	public String insert(){
		
		// 保存一个新用户
		String uid = userService.save(UserFactory.createUser());
		
		return uid;
	}
	
	@RequestMapping(value = "/query", method = RequestMethod.GET)
	public String query(String uid){
		
		// 查询该用户
		System.out.println(gson.toJson(userService.getUserById(uid), User.class));
		System.out.println();
		
		return "success";
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public String update(String uid, String username){
		
		User user = new User();
		user.setUid(uid);
		user.setUsername(username);
		
		// 更新该用户
		userService.update(user);
		
		
		return "success";
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public String del(String uid){
		
		// 删除该用户
		userService.del(uid);
		System.out.println();
		
		
		return "success";
	}
	
	
	
}

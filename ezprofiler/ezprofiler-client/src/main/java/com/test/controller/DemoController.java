package com.test.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.xjs.ezprofiler.annotation.Profiler;
import com.test.service.UserService;

/**
 * @author 605162215@qq.com
 *
 * @date 2018年4月12日 下午3:33:09<br/>
 */
@RestController
@Profiler
public class DemoController {
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	@GetMapping("/hello1")
	public String hello1() {
		Random rnd = new Random();
		try{Thread.sleep(rnd.nextInt(1000));}catch(Exception e) {}
		return "hello1";
	}
	
	@GetMapping("/hello2")
	public String hello2() {
		Random rnd = new Random();
		try{Thread.sleep(rnd.nextInt(1000));}catch(Exception e) {}
		return "hello2";
	}
	
	@GetMapping("/err")
	public String error() {
		Random rnd = new Random();
		try{Thread.sleep(rnd.nextInt(1000));}catch(Exception e) {}
		throw new RuntimeException();
	}
	
	@GetMapping("/world")
	@Profiler(false)
	public String world() {
		return "world";
	}
	
	@Autowired
	UserService userService;
	
	@GetMapping("/user")
	public String user() {
		return userService.getUserId();
	}
	
}

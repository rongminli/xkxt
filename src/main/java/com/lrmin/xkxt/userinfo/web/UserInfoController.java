package com.lrmin.xkxt.userinfo.web;

import com.lrmin.xkxt.userinfo.service.UserInfoService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/userInfo") 
public class UserInfoController {
	@Autowired
	private UserInfoService service;

}
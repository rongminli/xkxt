package com.lrmin.xkxt.userseduinfo.web;

import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lrmin.xkxt.userseduinfo.service.UserEduInfoService;

@RestController
@RequestMapping("/userEduInfo") 
public class UserEduInfoController {
	@Autowired
	private UserEduInfoService service;

}
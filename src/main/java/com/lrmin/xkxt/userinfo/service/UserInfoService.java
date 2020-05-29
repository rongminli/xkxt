package com.lrmin.xkxt.userinfo.service;

import com.lrmin.framework.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.lrmin.xkxt.userinfo.mapper.UserInfoMapper;
import com.lrmin.xkxt.userinfo.entity.UserInfo;

@Service 
public class UserInfoService extends AbstractService<UserInfo,UserInfoMapper> {
	@Autowired
	private UserInfoMapper mapper;

	@Override
	public boolean exit(UserInfo entity) {
		return false;
	}

}
package com.lrmin.xkxt.userseduinfo.service;

import com.lrmin.framework.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.lrmin.xkxt.userseduinfo.mapper.UserEduInfoMapper;
import com.lrmin.xkxt.userseduinfo.entity.UserEduInfo;

@Service 
public class UserEduInfoService extends AbstractService<UserEduInfo,UserEduInfoMapper> {
	@Override
	public boolean exit(UserEduInfo entity) {
		return false;
	}
}
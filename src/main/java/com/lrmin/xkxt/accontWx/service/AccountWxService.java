package com.lrmin.xkxt.accontWx.service;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.lrmin.framework.service.AbstractService;
import com.lrmin.xkxt.accontWx.mapper.AccountWxMapper;
import com.lrmin.xkxt.accontWx.entity.AccountWx;

@Service 
public class AccountWxService  extends AbstractService<AccountWx, AccountWxMapper>  {
	@Autowired
	private AccountWxMapper mapper;

@Override
	public boolean exit(AccountWx entity) {
		return findById(entity.getId()) == null;
	}

}
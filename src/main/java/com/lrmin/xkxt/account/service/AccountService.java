package com.lrmin.xkxt.account.service;

import com.lrmin.xkxt.account.entity.Account;
import com.lrmin.xkxt.account.mapper.AccountMapper;
import com.lrmin.framework.service.AbstractService;
import org.springframework.stereotype.Service;

@Service 
public class AccountService extends AbstractService<Account, AccountMapper> {

	@Override
	public boolean exit(Account entity) {
		return false;
	}
}
package com.lrmin.xkxt.userinfo.mapper;

import com.lrmin.framework.mapper.Dao;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.lrmin.xkxt.userinfo.entity.UserInfo;

@Mapper 
public interface UserInfoMapper extends Dao<UserInfo> {

	@Override
    public Long insert(UserInfo obj);

	@Override
	public Long insertSelective(UserInfo obj);

	@Override
	public Long deleteById(String id);

	@Override
	public Long update(UserInfo obj);

	@Override
	public Long updateSelective(UserInfo obj);

	@Override
	public List<UserInfo> findAll();

	@Override
    public List<UserInfo> findById(String id);

}
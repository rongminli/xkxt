package com.lrmin.xkxt.userseduinfo.mapper;

import com.lrmin.framework.mapper.Dao;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.lrmin.xkxt.userseduinfo.entity.UserEduInfo;

@Mapper 
public interface UserEduInfoMapper extends Dao<UserEduInfo> {

	@Override
    public Long insert(UserEduInfo obj);

	@Override
	public Long insertSelective(UserEduInfo obj);

	@Override
	public Long deleteById(String id);

	@Override
	public Long update(UserEduInfo obj);

	@Override
	public Long updateSelective(UserEduInfo obj);

	@Override
	public List<UserEduInfo> findAll();

	@Override
    public List<UserEduInfo> findById(String id);

}
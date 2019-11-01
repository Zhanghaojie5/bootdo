package com.bootdo.school.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.bootdo.school.domain.SchoolPO;

@Mapper
public interface SchoolDao {
	SchoolPO get(Long id);
	
	List<SchoolPO> list(Map<String,Object> map);
	
	int count(Map<String,Object> map);
	
	int save(SchoolPO content);
	
	int update(SchoolPO content);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}

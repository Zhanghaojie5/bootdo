package com.bootdo.school.service;

import java.util.List;
import java.util.Map;

import com.bootdo.school.domain.SchoolPO;

public interface SchoolService {
	SchoolPO get(Long id);
	
	List<SchoolPO> list(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(SchoolPO bContent);
	
	int update(SchoolPO bContent);
	
	int remove(Long id);
	
	int batchRemove(Long[] ids);
}

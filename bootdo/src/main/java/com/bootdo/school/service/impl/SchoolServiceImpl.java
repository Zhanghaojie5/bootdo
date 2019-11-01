package com.bootdo.school.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootdo.school.dao.SchoolDao;
import com.bootdo.school.domain.SchoolPO;
import com.bootdo.school.service.SchoolService;

@Service
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	private SchoolDao courseWareMapper;
	
	@Override
	public SchoolPO get(Long cid){
		return courseWareMapper.get(cid);
	}
	
	@Override
	public List<SchoolPO> list(Map<String, Object> map){
		return courseWareMapper.list(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return courseWareMapper.count(map);
	}
	
	@Override
	public int save(SchoolPO bContent){
		return courseWareMapper.save(bContent);
	}
	
	@Override
	public int update(SchoolPO bContent){
		return courseWareMapper.update(bContent);
	}
	
	@Override
	public int remove(Long cid){
		return courseWareMapper.remove(cid);
	}
	
	@Override
	public int batchRemove(Long[] cids){
		return courseWareMapper.batchRemove(cids);
	}

}

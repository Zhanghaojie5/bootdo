package com.bootdo.file.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import com.bootdo.activiti.dao.TestDao;
import com.bootdo.activiti.domain.TestStudentDO;
import com.bootdo.file.service.FileUploadService;

import org.springframework.transaction.annotation.Transactional;


@Service
public class FileUploadImpl implements FileUploadService {
	@Autowired
	private TestDao testDao;
	
	
	@Override
	public TestStudentDO get(String id){
		return testDao.get(id);
	}
	
	@Override
	public List<TestStudentDO> list(){
		return testDao.list();
	}
	@Override
	public List<TestStudentDO> listshow(Map<String, Object> map) {
		return testDao.listshow(map);
	}
	
	@Override
	public int count(Map<String, Object> map){
		return testDao.count(map);
	}

	@Transactional(rollbackFor=Exception.class)
	@Override
	public int save(TestStudentDO testStudent){
			testStudent.setId(UUID.randomUUID().toString().replace("-",""));
			return testDao.save(testStudent);
	}
	@Override
	public int update(TestStudentDO testStudent){
		return testDao.update(testStudent);
	}
	
	@Override
	public int remove(String id){
		return testDao.remove(id);
	}
	
	@Override
	public int batchRemove(String[] ids){
		return testDao.batchRemove(ids);
	}

	
	
}

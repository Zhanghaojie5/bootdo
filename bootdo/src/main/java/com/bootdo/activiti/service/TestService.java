package com.bootdo.activiti.service;

import com.bootdo.activiti.domain.TestStudentDO;
import com.bootdo.activiti.domain.TestStudentDO;

import java.util.List;
import java.util.Map;

/**
 * test_student
 * @author Duan
 * @date 2019-3-4 13:28:58
 */
public interface TestService {
	
	TestStudentDO get(String id);
	
	List<TestStudentDO> list();
	
	List<TestStudentDO> listshow(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestStudentDO salary);
	
	int update(TestStudentDO salary);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

package com.bootdo.file.dao;

import com.bootdo.activiti.domain.TestStudentDO;
import com.bootdo.activiti.domain.TestStudentDO;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * test_student
 * @author Duan
 * @date 2019-3-4 13:28:58
 */
@Mapper
public interface FileUpaloadDao {

	TestStudentDO get(String id);
	
	List<TestStudentDO> list();
	
	List<TestStudentDO> listshow(Map<String, Object> map);
	
	int count(Map<String, Object> map);
	
	int save(TestStudentDO salary);
	
	int update(TestStudentDO salary);
	
	int remove(String id);
	
	int batchRemove(String[] ids);
}

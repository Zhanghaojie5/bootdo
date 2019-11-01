package com.bootdo.activiti.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.bootdo.activiti.domain.TestStudentDO;
import com.bootdo.activiti.service.TestService;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;

/**
 * Hello world!
 *@author Duan
 */
@RestController
@RequestMapping("/test")
public class HelloWorldController {
	@Autowired
	private TestService testService;
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @RequestMapping("/list")
    public List<TestStudentDO> list() {
    	List<TestStudentDO> list=testService.list();
        return list;
    }
    @RequestMapping("/listforward")
    public ModelAndView listshow(){
	
		return new ModelAndView("act/test/listshow");
	}
    @RequestMapping("/listshow")
    public PageUtils list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);
		List<TestStudentDO> salaryList = testService.listshow(query);
		int total = testService.count(query);
		PageUtils pageUtils = new PageUtils(salaryList, total);
		return pageUtils;
	}
    @GetMapping("/add2")
	//@RequiresPermissions("oa:notify:add")
    ModelAndView add2() {
		return new ModelAndView("act/test/add2");
	}
    @GetMapping("/form")
	//@RequiresPermissions("activiti:salary:add")
    ModelAndView add(){
    	
		return new ModelAndView("act/test/add");
	}
	@GetMapping("/editForm/{id}")
	//@RequiresPermissions("activiti:salary:add")
	public ModelAndView editForm(@PathVariable String id){
    	TestStudentDO s=testService.get(id);
    	ModelAndView md=new ModelAndView("act/test/edit");
    	md.addObject("s", s);
		return md;
    	
    	
	}
	 @ResponseBody
	 @PostMapping("/edit")
	 //@RequiresPermissions("activiti:salary:add")
	 public R edit( TestStudentDO salary){
			
			if(testService.update(salary)>0){
				
				return R.ok();
			}
			return R.error();
		}
    @ResponseBody
	@PostMapping("/save")
	//@RequiresPermissions("activiti:salary:add")
	public R saveOrUpdate( TestStudentDO salary){
		
		if(testService.save(salary)>0){
			
			return R.ok();
		}
		return R.error();
	}
    @ResponseBody
	@PostMapping("/remove")
	//@RequiresPermissions("activiti:salary:add")
	public R remove(String id){
		if(testService.remove(id)>0){
			
			return R.ok();
		}
		return R.error();
	}

    @ResponseBody
	@PostMapping("/batchRemove")
	public R batchRemove(@RequestParam("ids[]") String[] ids){
		if(testService.batchRemove(ids)>0){
			return R.ok();
		}
		return R.error();
	}
}

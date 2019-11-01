package com.bootdo.file.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bootdo.activiti.domain.TestStudentDO;
import com.bootdo.activiti.service.SalaryService;
import com.bootdo.activiti.service.TestService;
import com.bootdo.activiti.utils.ActivitiUtils;
import com.bootdo.common.config.BootdoConfig;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.domain.FileDO;
import com.bootdo.common.utils.FileType;
import com.bootdo.common.utils.FileUtil;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.ShiroUtils;

/**
 * Hello world!
 *@author Duan
 */
@RestController
@RequestMapping("/file")
public class FileUploadController extends BaseController {
	@Autowired
	private TestService testService;
	@Autowired
	private BootdoConfig bootdoConfig;
	
    @RequestMapping("/hello")
    public String index() {
        return "Hello World";
    }
    @RequestMapping("/list")
    public List<TestStudentDO> list() {
    	List<TestStudentDO> list=testService.list();
        return list;
    }
    @RequestMapping("/fileforward")
    public ModelAndView listshow(){
	
		return new ModelAndView("file/upload/file");
	}
    @ResponseBody
	@PostMapping("/upload")
	R upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		if ("test".equals(getUsername())) {
			return R.error(1, "演示系统不允许修改,完整体验请部署程序");
		}
		String fileName = file.getOriginalFilename();
		fileName = FileUtil.renameToUUID(fileName);
		FileDO sysFile = new FileDO(FileType.fileType(fileName), "/files/" + fileName, new Date());
		try {
			FileUtil.uploadFile(file.getBytes(), bootdoConfig.getUploadPath(), fileName);
			System.out.println("bootdoConfig.getUploadPath()="+bootdoConfig.getUploadPath());
		} catch (Exception e) {
			return R.error();
		}

		/*if (sysFileService.save(sysFile) > 0) {
			return R.ok().put("fileName",sysFile.getUrl());
		}*/
		return R.ok();
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
	 public int edit( TestStudentDO salary){
			
			if(testService.update(salary)>0){
				
				return 0;
			}
			return 1;
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
	public int remove(String id){
		if(testService.remove(id)>0){
			
			return 0;
		}
		return 1;
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

package com.bootdo.school.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bootdo.common.config.Constant;
import com.bootdo.common.controller.BaseController;
import com.bootdo.common.utils.PageUtils;
import com.bootdo.common.utils.Query;
import com.bootdo.common.utils.R;
import com.bootdo.common.utils.StringUtils;
import com.bootdo.school.domain.SchoolPO;
import com.bootdo.school.service.SchoolService;

@Controller
@RequestMapping("/school/home")
public class SchoolController extends BaseController {

	@Autowired
	private SchoolService schoolService;
	
	@GetMapping()
	String blog() {
		return "school/home/main";
	}
	
	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("school:home:main")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		Query query = new Query(params);
		List<SchoolPO> schoolList = schoolService.list(query);
		int total = schoolService.count(query);
		PageUtils pageUtils = new PageUtils(schoolList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("school:home:add")
	String add() {
		return "school/home/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("school:home:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		SchoolPO schoolPO = schoolService.get(id);
		model.addAttribute("school", schoolPO);
		return "school/home/edit";
	}
	
	/**
	 * 保存
	 */
	@ResponseBody
	@RequiresPermissions("school:home:add")
	@PostMapping("/save")
	public R save(SchoolPO school) {
		int count;
		
		if (school.getId() == null) {
			school.setDelTag(0);
			count = schoolService.save(school);
		} else {
			count = schoolService.update(school);
		}
		if (count > 0) {
			return R.ok().put("id", school.getId());
		}
		return R.error();
	}

	/**
	 * 修改
	 */
	@RequiresPermissions("school:home:edit")
	@ResponseBody
	@RequestMapping("/update")
	public R update( SchoolPO school) {
		schoolService.update(school);
		return R.ok();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("school:home:remove")
	@PostMapping("/remove")
	@ResponseBody
	public R remove(Long id) {
		if (schoolService.remove(id) > 0) {
			return R.ok();
		}
		return R.error();
	}

	/**
	 * 删除
	 */
	@RequiresPermissions("school:home:batchRemove")
	@PostMapping("/batchRemove")
	@ResponseBody
	public R remove(@RequestParam("ids[]") Long[] cids) {
		schoolService.batchRemove(cids);
		return R.ok();
	}
}

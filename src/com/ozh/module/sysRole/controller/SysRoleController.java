package com.ozh.module.sysRole.controller;

import com.ozh.common.Enum.BoolCodeEnum;
import com.ozh.common.Enum.RoleTypeCodeEnum;
import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.SysRole;
import com.ozh.core.service.SysRoleService;
import com.ozh.core.service.SysUserService;
import com.ozh.utils.SpringContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/sysRole")
public class SysRoleController {
	private final static String SUCCESS = "success";
	protected SysRoleService getSysUserService(){
		return SpringContextHolder.getBean(SysRoleService.class);
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/findSysRoleList",method = RequestMethod.GET)
	@ResponseBody
	public Object findSysRoleList(Integer offset, Integer limit,String search,Pageable pageable) throws  BusinessException, ParseException {
		PageRequest pageRequest = new PageRequest(offset/limit,limit,pageable.getSort());
		Page page = getSysUserService().findSysRoleList(pageRequest, search);
		return  new PageImpl<SysRole>(page.getContent(),pageable,page.getTotalElements());

	}
	@RequestMapping(value = "/saveOrUpdateSysRole",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateSysRole(SysRole sysRole){
		SysRole role = new SysRole();
		BeanUtils.copyProperties(sysRole, role);
		role.setRoleTypeCode(RoleTypeCodeEnum.FRONT.toCode());
		if(role.getId()==null) {
			role.setCreateDate(new Date());
		}
		role.setScore(0);
		getSysUserService().save(role);
		ModelMap model = new ModelMap();
		model.put(SUCCESS, true);
		return model;
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public  Object delete(Long id){
		getSysUserService().delete(id);
		ModelMap model = new ModelMap();
		model.put(SUCCESS, true);
		return model;
	}
	@RequestMapping(value = "/findOne",method = RequestMethod.POST)
	@ResponseBody
	public Object findOne(Long id){
		return 	getSysUserService().findOne(id);
	}
}


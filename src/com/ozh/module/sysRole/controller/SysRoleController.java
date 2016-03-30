package com.ozh.module.sysRole.controller;

import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.SysRole;
import com.ozh.core.service.SysRoleService;
import com.ozh.utils.SpringContextHolder;
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
	public Object findSysRoleList(ModelMap model, Integer offset, Integer limit,String search,Pageable pageable) throws  BusinessException, ParseException {
		PageRequest pageRequest = new PageRequest(offset,limit,pageable.getSort());
		Page page = getSysUserService().findSysRoleList(pageRequest, search);
		return  new PageImpl<SysRole>(page.getContent(),pageable,page.getTotalElements());

	}

}


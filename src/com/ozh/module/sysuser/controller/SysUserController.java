package com.ozh.module.sysuser.controller;

import com.ozh.common.Enum.BoolCodeEnum;
import com.ozh.common.Global;
import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.SysUser;
import com.ozh.core.service.SysUserService;
import com.ozh.utils.SpringContextHolder;
import com.ozh.web.WebContextFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	private final static String SUCCESS = "success";


	@RequestMapping(value = "/findOne",method = RequestMethod.POST)
	@ResponseBody
	public Object findOne(Long id){
		return 	SpringContextHolder.getBean(SysUserService.class).findOne(id);
	}

	@RequestMapping(value = "/addSysUser",method = RequestMethod.POST)
	@ResponseBody
	public Object addSysUser(ModelMap model,SysUser sysUser){
		SysUser user = new SysUser();
		BeanUtils.copyProperties(sysUser, user);
		user.setIsAdmin(BoolCodeEnum.NO.toCode());
		user.setLastPswModifyTime(new Date());
		user.setRegisterDate(new Date());
		user.setLastBuyTime(new Date());
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());

		SpringContextHolder.getBean(SysUserService.class).save(user);
		model.put(SUCCESS, true);
		return model;
	}
	@RequestMapping(value = "/updateSysUser",method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysUser(ModelMap model,SysUser sysUser){
		SysUser user = new SysUser();
		BeanUtils.copyProperties(sysUser, user);
		user.setIsAdmin(BoolCodeEnum.NO.toCode());
		user.setLastPswModifyTime(new Date());
		user.setRegisterDate(new Date());
		user.setLastBuyTime(new Date());
		user.setCreateDate(new Date());
		user.setUpdateDate(new Date());

//		SpringContextHolder.getBean(SysUserService.class).(user);
		model.put(SUCCESS, true);
		return model;
	}


	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public  Object delete(@RequestBody List<Long> ids){
		return null;
	}



	@RequestMapping(value = "/register",method = RequestMethod.POST)
	@ResponseBody
	public  Object registerUserFrom(ModelMap model,SysUser sysUser)
	{
		sysUser.setUserName("ozh");
		sysUser.setLoginId("XX");
		sysUser.setUserPsw("asdqwe");
		SysUser sysUser1 = SpringContextHolder.getBean(SysUserService.class).registerUser(sysUser);
		model.put(SUCCESS,true);
		model.put("user",sysUser1);
		return model;
	}
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	@ResponseBody
	public  Object login(ModelMap model,String loginId,String password)
	{
		SysUser sysUser = getSysUserService().login(loginId, password);
		return model;
	}

	@RequestMapping(value = "/list",method = RequestMethod.GET)
	@ResponseBody
	public Object list(ModelMap model,@PageableDefault(page = 0, size = 10) Pageable pageable){
		return null;
	}


	protected SysUserService getSysUserService(){
		return SpringContextHolder.getBean(SysUserService.class);
	}

	/**
	 * 退出
	 * @param model
	 * @return
	 * @throws BusinessException
	 */
	@RequestMapping(value = "/exit",method= RequestMethod.GET)
	public String userExit(ModelMap model) throws BusinessException {
		WebContextFactory.getWebContext().removeSessionAttr(Global.FRONT_USER);
		//登陆时有将lastLoginTime放入session，离开时需要remove
//		WebContextFactory.getWebContext().removeSessionAttr(Global.FRONT_USER_LAST_LOGIN_TIME);
		return "redirect:/index.ac";
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/findUserList",method = RequestMethod.GET)
	@ResponseBody
	public Object findUserList(ModelMap model, Integer offset, Integer limit,String search,Pageable pageable) throws  BusinessException, ParseException {
		PageRequest pageRequest = new PageRequest(offset,limit,pageable.getSort());
		Page page = getSysUserService().findUserList(pageRequest,search);
		return  new PageImpl<SysUser>(page.getContent(),pageable,page.getTotalElements());

	}

}


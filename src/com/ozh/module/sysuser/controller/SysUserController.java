package com.ozh.module.sysuser.controller;

import com.ozh.common.Global;
import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.SysUser;
import com.ozh.core.service.SysUserService;
import com.ozh.utils.SpringContextHolder;
import com.ozh.web.WebContextFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sysUser")
public class SysUserController {
	private final static String SUCCESS = "success";


	@RequestMapping(value = "/findOne",method = RequestMethod.GET)
	@ResponseBody
	public Object findOne(Long id){
		return null;
	}

	@RequestMapping(value = "/saveOrUpdate",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdate(){

		SysUser sysUser = new SysUser();
		sysUser.setUserName("ozh");
		sysUser.setUserPsw("asdqwe");
		getSysUserService().save(sysUser);
		System.out.println(sysUser);

		return null;
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

}


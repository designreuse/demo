package com.ozh.core.service.impl;



import com.ozh.common.Enum.BoolCodeEnum;
import com.ozh.common.Enum.UserSexCodeEnum;
import com.ozh.common.Global;
import com.ozh.common.service.impl.AbstractBaseService;
import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.SysRole;
import com.ozh.core.entity.SysUserRoleRelationship;
import com.ozh.core.service.SysRoleService;
import com.ozh.core.service.SysUserRoleRelationshipService;
import com.ozh.utils.SpringContextHolder;
import com.ozh.web.WebContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.ozh.core.entity.SysUser;
import com.ozh.core.repository.SysUserRepository;
import com.ozh.core.service.SysUserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * (服务层实现)
 * @author by ozh core generator
 * @version 1.0.0
 */
@Service
public class SysUserServiceImpl extends AbstractBaseService<SysUser, Long> implements SysUserService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysUserRepository getSysUserRepository() {
		return (SysUserRepository) baseRepository;
	}


	@Override
	@Transactional
	 public SysUser registerUser(SysUser sysUser) throws BusinessException {
		SysUser user = new SysUser();
		BeanUtils.copyProperties(sysUser, user);
		user.setIsDelete(BoolCodeEnum.NO.toCode());
		user.setIsAdmin(BoolCodeEnum.NO.toCode());
		user.setLastPswModifyTime(new Date());
		if(user.getUserSexCode() == null){
			user.setUserSexCode(UserSexCodeEnum.SECRET.toCode());
		}
		save(user);
		//角色关系
		saveFrontSysUser(user, Global.DEFAULT_FRONT_USER_LEVEL);

		WebContextFactory.getWebContext().setFrontEndUser(sysUser);
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public SysUser login(String loginId, String password) throws BusinessException {
		SysUser sysUser = getSysUserRepository().findByLoginId(loginId);
		if(sysUser != null){
			if(password.indexOf(sysUser.getUserPsw()) > -1){
				throw new BusinessException("errors.login.password", new String[]{"密码错误！"});
			}
			WebContextFactory.getWebContext().setFrontEndUser(sysUser);
			return sysUser;
		}
		throw new BusinessException("errors.login.null", new String[]{"不存在用户！"});
	}
	@Override
	@Transactional
	// 保存用户 和 机构的关系
	public void saveFrontSysUser(SysUser sysUser, Integer roleId) throws BusinessException{
		if(roleId != null){
			SysRole sysRole = SpringContextHolder.getBean(SysRoleService.class).findOne(roleId.longValue());
			if(sysRole == null){
				return;
			}
			//保存用户 和 角色 的 关系
			SysUserRoleRelationship sysUserRoleRelationship = new SysUserRoleRelationship();
			sysUserRoleRelationship.setSysUserId(sysUser.getId().intValue());
			sysUserRoleRelationship.setSysRoleId(sysRole.getId().intValue());
			SpringContextHolder.getBean(SysUserRoleRelationshipService.class).save(sysUserRoleRelationship);
		}

	}
}
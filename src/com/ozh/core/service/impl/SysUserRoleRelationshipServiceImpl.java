package com.ozh.core.service.impl;



import com.ozh.common.service.impl.AbstractBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.ozh.core.entity.SysUserRoleRelationship;
import com.ozh.core.repository.SysUserRoleRelationshipRepository;
import com.ozh.core.service.SysUserRoleRelationshipService;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
public class SysUserRoleRelationshipServiceImpl extends AbstractBaseService<SysUserRoleRelationship, Long> implements SysUserRoleRelationshipService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private SysUserRoleRelationshipRepository getSysUserRoleRelationshipRepository() {
		return (SysUserRoleRelationshipRepository) baseRepository;
	}

}
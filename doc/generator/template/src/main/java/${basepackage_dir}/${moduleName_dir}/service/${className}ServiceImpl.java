<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package com.ozh.${basepackage}.${moduleName}.service;


import com.ozh.common.service.impl.AbstractBaseService;
import ${basepackage}.${moduleName}.entity.${className};
import ${basepackage}.${moduleName}.repository.${className}Repository;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

<#include "/java_imports.include">
@Service
public interface ${className}ServiceImpl extends AbstractBaseService<${className},Long> implements ${className}Service{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
	private ${className}Repository get${className}Repository() {
		return (${className}Repository) baseRepository;
	}

}

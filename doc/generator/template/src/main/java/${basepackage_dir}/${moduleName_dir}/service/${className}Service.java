<#assign className = table.className>
<#assign classNameLower = className?uncap_first>
package ${basepackage}.${moduleName}.service;


import ${basepackage}.${moduleName}.entity.${className};
import com.ozh.common.service.IBaseService;

/**
 * ${table.remarks}(服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ${className}Service extends IBaseService<${className}, Long>{

}

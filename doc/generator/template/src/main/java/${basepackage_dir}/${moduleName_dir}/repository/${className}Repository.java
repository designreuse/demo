<#assign className = table.className>

package ${basepackage}.${moduleName}.repository;

import ${basepackage}.${moduleName}.entity.*;
import com.ozh.common.repository.IBaseRepository;

/**
 * ${table.remarks}(JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ${className}Repository extends  IBaseRepository<${className}, Long>,${className}RepositoryCustom {


}


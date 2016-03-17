
package com.ozh.core.repository;

import com.ozh.core.entity.*;
import com.ozh.common.repository.IBaseRepository;

import java.util.List;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ShoppingCartListRepository extends  IBaseRepository<ShoppingCartList, Long>,ShoppingCartListRepositoryCustom {
//    @Query("select u from SysResource u where u.parentId = :parentId and u.resourceType = :resourceType")
    List<ShoppingCartList> findByUserIdAndIsDelete(Integer userId,String isDelete);
}


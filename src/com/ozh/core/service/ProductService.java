package com.ozh.core.service;


import com.ozh.core.entity.Product;
import com.ozh.common.service.IBaseService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * (服务层类)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ProductService extends IBaseService<Product, Long>{
    Page findProductList(Pageable pageable,String search);
}

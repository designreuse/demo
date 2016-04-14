package com.ozh.core.service.impl;


import com.ozh.common.service.impl.AbstractBaseService;
import com.ozh.core.entity.Product;
import com.ozh.core.repository.ProductRepository;
import com.ozh.core.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
public class ProductImpl extends AbstractBaseService<Product, Long> implements ProductService {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@SuppressWarnings("unused")
    private ProductRepository getProductRepository() {
		return (ProductRepository) baseRepository;
	}

	@Override
	public Page findProductList(Pageable pageable, String search) {
		return getProductRepository().findProductList(pageable,search);
	}
}
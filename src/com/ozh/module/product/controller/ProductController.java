package com.ozh.module.product.controller;

import com.ozh.common.utils.BusinessException;
import com.ozh.core.entity.Product;
import com.ozh.core.entity.SysRole;
import com.ozh.core.service.ProductService;
import com.ozh.utils.SpringContextHolder;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.Date;

@Controller
@RequestMapping("/product")
public class ProductController {
	private final static String SUCCESS = "success";
	protected ProductService getProductService(){
		return SpringContextHolder.getBean(ProductService.class);
	}
	/**
	 * 列表
	 */
	@RequestMapping(value = "/findProductList",method = RequestMethod.GET)
	@ResponseBody
	public Object findProductList(Integer offset, Integer limit,String search,Pageable pageable) throws  BusinessException, ParseException {
		PageRequest pageRequest = new PageRequest(offset/limit,limit,pageable.getSort());
		Page page = getProductService().findProductList(pageRequest, search);
		return  new PageImpl<SysRole>(page.getContent(),pageable,page.getTotalElements());

	}
	@RequestMapping(value = "/saveOrUpdateSysRole",method = RequestMethod.POST)
	@ResponseBody
	public Object saveOrUpdateSysRole(Product product){
		Product product1 = new Product();
		BeanUtils.copyProperties(product, product1);
		if(product1.getId()==null) {
			product1.setCreateDate(new Date());
		}
		getProductService().save(product1);
		ModelMap model = new ModelMap();
		model.put(SUCCESS, true);
		return model;
	}

	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public  Object delete(Long id){
		getProductService().delete(id);
		ModelMap model = new ModelMap();
		model.put(SUCCESS, true);
		return model;
	}
	@RequestMapping(value = "/findOne",method = RequestMethod.POST)
	@ResponseBody
	public Object findOne(Long id){
		return 	getProductService().findOne(id);
	}
}


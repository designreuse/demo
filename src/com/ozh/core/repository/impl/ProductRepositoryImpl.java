
package com.ozh.core.repository.impl;

import com.ozh.core.entity.Product;
import com.ozh.core.repository.ProductRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Override
    public Page findProductList(Pageable pageable, String search) {
        StringBuffer sql = new StringBuffer();
        sql.append(" from prd_product a where 1=1 ");
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(search)) {
            sql.append(" AND a.PRODUCT_NM LIKE :productNm");
            paramMap.put(Product.PRODUCT_NM, "%" + search + "%");
        }
        int pageSize = pageable.getPageSize();
        int firstIdx = pageable.getPageNumber() * pageSize;
        Long total = new NamedParameterJdbcTemplate(this.jdbcTemplate).queryForObject("select count(*)"+ sql, paramMap, Long.class);
        List<Product> resultList = new NamedParameterJdbcTemplate(this.jdbcTemplate)
                .query("select a.* " + QueryUtils.applySorting(sql.toString(), pageable.getSort(), "a") + " limit " + firstIdx + "," + pageSize, paramMap, new BeanPropertyRowMapper(Product.class));

        return new PageImpl<Product>(resultList, pageable, total);
    }
}

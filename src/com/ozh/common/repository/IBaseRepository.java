package com.ozh.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kosam on 2016/2/28.
 * 持久层 基本借口
 */
@NoRepositoryBean
public interface IBaseRepository<M, ID extends Serializable> extends JpaRepository<M, ID> {

    /**
     * 使用批量删除方式
     * @param ids
     */
    void delete(List<ID> ids);
}

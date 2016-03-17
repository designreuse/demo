package com.ozh.common.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import com.ozh.common.entity.BaseEntity;
import com.ozh.common.repository.IBaseRepository;
import com.ozh.common.service.IBaseService;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ozh on 2016/2/28.
 * 基础服务类 实现
 */
public abstract class AbstractBaseService<M extends BaseEntity, ID extends Serializable> implements IBaseService<M, ID> {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    protected IBaseRepository<M, ID> baseRepository;

    @Autowired
    public void setBaseRepository(IBaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Override
    public M save(M m) {
        return this.baseRepository.save(m);
    }

    @Override
    public M saveAndFlush(M m) {
        m = save(m);
        this.baseRepository.flush();
        return m;
    }

    @Override
    public void delete(ID id) {
        this.baseRepository.delete(id);
    }

    @Override
    public void delete(M m) {
        this.baseRepository.delete(m);
    }

    @Override
    public void delete(List<ID> ids) {
        this.baseRepository.delete(ids);
    }

    @Override
    public M findOne(ID id) {
        return this.baseRepository.findOne(id);
    }

    @Override
    public boolean exists(ID id) {
        return this.baseRepository.exists(id);
    }

    @Override
    public long count() {
        return this.baseRepository.count();
    }

    @Override
    public List<M> findAll() {
        return this.baseRepository.findAll();
    }

    @Override
    public List<M> findAll(Sort sort) {
        return this.baseRepository.findAll(sort);
    }

    @Override
    public Page<M> findAll(Pageable pageable) {
        return this.baseRepository.findAll(pageable);
    }
}

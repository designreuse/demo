package com.ozh.common.service;

/**
 * Created by ksoam on 2016/2/27.
 */
public interface BaseService {

    /**
     * 保存实体
     * @param entity
     */
    void save(Object entity);

    /**
     * 根据主键获取对象
     * @param <T>
     * @param clazz 实体类
     * @param id    主键
     * @return
     */
    <T> T getById(Class<T> clazz,Object id);
}

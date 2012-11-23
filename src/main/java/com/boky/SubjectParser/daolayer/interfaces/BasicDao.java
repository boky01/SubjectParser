package com.boky.SubjectParser.daolayer.interfaces;

import java.io.Serializable;
import java.util.List;

public interface BasicDao<T> {
    public void saveOrUpdate(T entity);

    /**
     * Get a entity by id.
     * @param id id of the searched entity.
     * @return With the entity which has the given id. 
     */
    public T getById(Serializable id);

    /**
     * Delete entity from DB by id.
     * @param id Id of the entity.
     */
    public void deleteById(Serializable id);

    /**
     * Get all entity from DB.
     * @return all entity from DB.
     */
    public List<T> getAllEntity();

}

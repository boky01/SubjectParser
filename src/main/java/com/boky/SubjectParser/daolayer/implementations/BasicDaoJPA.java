/**
 * 
 */
package com.boky.SubjectParser.daolayer.implementations;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.boky.SubjectParser.daolayer.interfaces.BasicDao;

/**
 * @author Andras_Bokor
 *
 */
@Repository
public abstract class BasicDaoJPA<T> implements BasicDao<T> {
    @PersistenceContext
    private EntityManager entityManager;

    private Class<T> type;

    /**
     * It contains the common used methods by specific DAOs.
     */
    @SuppressWarnings("unchecked")
    public BasicDaoJPA() {
        Type t = getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType) t;
        type = (Class<T>) pt.getActualTypeArguments()[0];
    }

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.daolayer.interfaces.BasicDao#saveOrUpdate(java.lang.Object)
     */
    @Override
    //TODO ezen meg gondolkodni 
    public void saveOrUpdate(T entity) {
        if (this.getById(getEntityId(entity)) == null) {
            getEntityManager().persist(entity);
        } else {
            if (!entityManager.contains(entity)) {
                getEntityManager().merge(entity);
            }
        }

    }

    private Serializable getEntityId(T entity) {
        Method method;
        Serializable result = null;
        try {
            method = type.getMethod("getId");
            result = (Serializable) method.invoke(entity);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
            LoggerFactory.getLogger(type).error(e.getMessage(), e);
        }
        return result;
    }

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.daolayer.interfaces.BasicDao#findById(java.lang.Object)
     */
    @Override
    public T getById(Serializable id) {
        if (id == null) {
            return null;
        }
        return getEntityManager().find(type, id);
    }

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.daolayer.interfaces.BasicDao#deleteById(java.lang.Object)
     */
    @Override
    public void deleteById(Serializable id) {
        this.getEntityManager().remove(getById(id));
    }

    /* (non-Javadoc)
     * @see com.boky.SubjectParser.daolayer.interfaces.BasicDao#getAllEntity()
     */
    @Override
    public List<T> getAllEntity() {
        return runOwnQuery("FROM " + type.getSimpleName());
    }

    @SuppressWarnings("unchecked")
    protected List<T> runOwnQuery(String query, Object... params) {
        return this.createQuery(query, params).getResultList();
    }

    private Query createQuery(String query, Object[] params) {
        Query createdQuery = getEntityManager().createQuery(query);
        for (int i = 0; i < params.length; i++) {
            createdQuery.setParameter(String.valueOf(i + 1), params[i]);
        }
        return createdQuery;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}

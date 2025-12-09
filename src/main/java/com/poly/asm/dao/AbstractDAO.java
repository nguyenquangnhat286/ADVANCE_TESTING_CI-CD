package com.poly.asm.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import com.poly.asm.util.JpaUtil;

public class AbstractDAO<T> {
    public static final EntityManager em = JpaUtil.getEntityManager();

    @Override
    protected void finalize() throws Throwable {
        em.close();
        super.finalize();
    }

    public T findById(Class<T> clazz, Object id) {
        return em.find(clazz, id);
    }

    public List<T> findAll(Class<T> clazz, boolean existIsActive) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive) {
            sql.append(" WHERE Active = 1");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
        return query.getResultList();
    }

    public List<T> findAll(Class<T> clazz, boolean existIsActive, int pageNumber, int pageSize) {
        String entityName = clazz.getSimpleName();
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT o FROM ").append(entityName).append(" o");
        if (existIsActive) {
            sql.append(" WHERE Active = 1");
        }
        TypedQuery<T> query = em.createQuery(sql.toString(), clazz);
        query.setFirstResult((pageNumber - 1) * pageSize);
        query.setMaxResults(pageSize);
        return query.getResultList();
    }

    public T create(T entity) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.persist(entity);
            trans.commit();
            return entity;
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        }
    }

    public T update(T entity) {
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(entity);
            trans.commit();
            return entity;
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        }
    }

    public T remove(Object id, Class<T> clazz) { // Sửa lại hàm remove chuẩn
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            T entity = em.find(clazz, id);
            em.remove(entity);
            trans.commit();
            return entity;
        } catch (Exception e) {
            trans.rollback();
            throw new RuntimeException(e);
        }
    }
}
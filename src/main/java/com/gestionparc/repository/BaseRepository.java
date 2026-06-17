package com.gestionparc.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.gestionparc.util.HibernateUtil;
import java.util.List;

/**
 * Classe générique BaseRepository pour les opérations CRUD
 */
public abstract class BaseRepository<T, ID> {

    protected Class<T> entityClass;

    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * Sauvegarde une entité
     */
    public ID save(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            ID id = (ID) session.save(entity);
            transaction.commit();
            return id;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la sauvegarde: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Met à jour une entité
     */
    public void update(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.merge(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la mise à jour: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Supprime une entité
     */
    public void delete(T entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Erreur lors de la suppression: " + e.getMessage(), e);
        } finally {
            session.close();
        }
    }

    /**
     * Récupère une entité par son ID
     */
    public T findById(ID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(entityClass, id);
        } finally {
            session.close();
        }
    }

    /**
     * Récupère toutes les entités
     */
    public List<T> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.createQuery("FROM " + entityClass.getSimpleName(), entityClass).list();
        } finally {
            session.close();
        }
    }
}

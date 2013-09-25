package edu.chl.hajo.shop.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.*;

/**
 * A container for entities, base class for OrderBook, ProductCatalogue,
 * CustomerRegistry The fundamental common operations are here (CRUD).
 *
 * T is type for items in container K is type of id (primary key)
 *
 * @author hajo
 */
public abstract class AbstractDAO<T , K>
        implements IDAO<T, K> {

    private EntityManagerFactory emf;
    private final Class<T> clazz;
    protected AbstractDAO(Class<T> clazz, String puName) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(puName);
    }
    
    @Override
    public void add(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void remove(K id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T ref = em.getReference(clazz, id);
        em.remove(ref);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public T update(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T result = em.merge(t);
        em.getTransaction().commit();
        em.close();
        return result;
    }

    @Override
    public T find(K id) {
        EntityManager em = emf.createEntityManager();
        return em.find(clazz, id);
    }

    @Override
    public List<T> getRange(int first, int nItems) {
        return null;
        
    }

    @Override
    public int getCount() {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT count(c) FROM "+clazz.getSimpleName()+" c", Long.class).getSingleResult();
        return count.intValue();
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }
    
    
}

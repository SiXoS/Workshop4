package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * All customers
 *
 * @author hajo
 */
public final class CustomerRegistry extends AbstractDAO<Customer, Long> implements ICustomerRegistry {
    
    public CustomerRegistry(String puName) {
        super(Customer.class, puName);
    }

    @Override
    public List<Customer> getByName(String name) {
        EntityManager em = getEmf().createEntityManager();
        return em.createQuery("SELECT c FROM Customer c WHERE c.fname = '"+name+"'", Customer.class).getResultList();
    }
}

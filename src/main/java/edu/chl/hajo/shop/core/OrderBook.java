package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * All orders
 *
 * @author hajo
 */
public final class OrderBook extends AbstractDAO<PurchaseOrder, Long>
        implements IOrderBook {


    public OrderBook(String puName) {
        super(PurchaseOrder.class,puName);
    }

    @Override
    public List<PurchaseOrder> getByCustomer(Customer c) {
        EntityManager em = getEmf().createEntityManager();
        return em.createQuery("SELECT p FROM PurchaseOrder p WHERE p.customer.id = "+c.getId(), PurchaseOrder.class).getResultList();
    }
    
    @Override
    public void removeOrderItem( OrderItem oi ){
        EntityManager em = getEmf().createEntityManager();
        em.getTransaction().begin();
        OrderItem oiRef = em.getReference(OrderItem.class, oi.getId());
        em.remove(oiRef);
        em.getTransaction().commit();
        em.close();
    }
}

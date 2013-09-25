package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;

/**
 * All products
 *
 * @author hajo
 */
public class ProductCatalogue extends AbstractDAO<Product, Long>
        implements IProductCatalogue {

    public ProductCatalogue(String puName) {
        super(Product.class, puName);
    }

    @Override
    public List<Product> getByName(String name) {
        EntityManager em = getEmf().createEntityManager();
        return em.createQuery("SELECT p FROM Product p WHERE p.name = '"+name+"'", Product.class).getResultList();
    }
    
    @Override
    public Product getById(Long id){
        return this.find(id);
    }
}

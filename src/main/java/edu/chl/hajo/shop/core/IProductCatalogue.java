
package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.IDAO;
import java.util.List;

/**
 * Interface to product catalogue
 * @author hajo
 */
public interface IProductCatalogue extends IDAO<Product, Long> {

    public List<Product> getByName(String name);
    
    public Product getById(Long id);
     
}

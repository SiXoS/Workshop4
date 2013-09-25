package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.IDAO;
import java.util.List;

/**
 * Interface to customer registry
 * @author hajo
 */
public interface ICustomerRegistry extends IDAO<Customer, Long> {

    List<Customer> getByName(String name);
    
}


package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.IDAO;
import java.util.List;

/**
 * Interface to order book
 * @author hajo
 */
public interface IOrderBook extends IDAO<PurchaseOrder, Long> {

    List<PurchaseOrder> getByCustomer(Customer c);
    
    public void removeOrderItem( OrderItem po );
    
}

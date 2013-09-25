package edu.chl.hajo.shop.core;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * Shop is a container for other containers
 * NOTE: Uses Java 1.7
 *
 * @author hajo
 */
public class JPAShop implements IShop {

    private final IProductCatalogue productCatalogue;
    private final ICustomerRegistry customerRegistry;
    private final IOrderBook orderBook;

    public JPAShop(String puName) {
        Logger.getAnonymousLogger().log(Level.INFO, "Shop alive {0}", this.hashCode());
        orderBook = new OrderBook(puName);
        customerRegistry = new CustomerRegistry(puName);
        productCatalogue = new ProductCatalogue(puName);
    }

    @Override
    public ICustomerRegistry getCustomerRegistry() {
        return customerRegistry;
    }

    @Override
    public IOrderBook getOrderBook() {
        return orderBook;
    }

    @Override
    public IProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }
}

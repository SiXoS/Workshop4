/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.hajo.shop.core;

import java.util.List;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test for Product catalog.
 *
 * NOTES: - Possible to run with embedded Derby (no server needed) - Possible to
 * run with server and create tables
 *
 * NOTE: Must have Table generation Strategy to Create (or Drop and Create)
 * NOTE: equals and hashCode can (should) possible be implemented in some other
 * way, though no common accepted way..
 *
 * @author hajo
 */
public class TestProductCatalogue {

    static IShop shop;
    final static String PU = "shop_pu";
    final static String TEST_PU = "shop_test_pu";

    @Before // Run before each test
    public void before() {
        shop = JPAShopFactory.getShop(TEST_PU);
    }
    
    @Test
    public void testGetRange() {
        IProductCatalogue pc = shop.getProductCatalogue();
        
        for( int i = 0; i < 10 ; i++){
            pc.add(new Product(String.valueOf(i), i));
        }
        List<Product> ps = pc.getRange(0, 2);
        assertTrue(ps.size() == 2);
    }

    @Test
    public void testAddUpdateAndRemoveProduct() {
        IProductCatalogue pc = shop.getProductCatalogue();

        Product p1 = new Product("banana", 11.11);
        pc.add(p1);
        assertTrue(pc.getCount() == 1);

        Product p2 = pc.find(p1.getId());
        // Not same transaction
        assertTrue(p2 != p1);
        // Equal by value
        assertTrue(p2.equals(p1));

        Product p = new Product(p1.getId(), "updated", p1.getPrice());
        p1 = pc.update(p);
        /*
         * Id NOT changed here we have two Products with
         * same id but different state! 
         * Seems to be no single simple solution to this..?!
         */
        assertTrue(p2.equals(p1));
        assertFalse(p2.getName().equals(p1.getName()));
        assertTrue(pc.getCount() == 1);

        pc.remove(p1.getId());
        assertTrue(pc.getCount() == 0);
        assertTrue(pc.find(p1.getId()) == null);

        // No change in program (but deleted from database)
        assertTrue(p2.equals(p1));
        assertFalse(p2.getName().equals(p1.getName()));
    }

    @Test
    public void testGetByName() {
        IProductCatalogue pc = shop.getProductCatalogue();
        Product p = new Product("banana", 11.11);
        pc.add(p);
        
        List<Product> ps = pc.getByName("banana");
        assertTrue(ps.size() == 1);
    }
}

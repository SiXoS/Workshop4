package edu.chl.hajo.shop.core;

import edu.chl.hajo.shop.utils.AbstractEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
/**
 * A purchase order
 *
 * @author hajo
 */
@Entity
public class PurchaseOrder extends AbstractEntity {
    // State of order
    public enum State {

        ACCEPTED,
        CANCELLED,
        INVOICED,
        UNINVOIDED,
        SHIPPED,
    }
    @Temporal(TemporalType.DATE)
    private Date date = new Date();
    @OneToMany(cascade = {CascadeType.ALL})
    private List<OrderItem> items;
    @OneToOne(cascade = {CascadeType.REFRESH})
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private State state = State.ACCEPTED;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Customer customer, List<OrderItem> items) {
        this.customer = customer;
        this.items = items;
    }

    public PurchaseOrder(Long id, Customer customer, List<OrderItem> items) {
        super(id);
        this.customer = customer;
        this.items = items;
    }

    public Date getDate() {
        return date;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Customer getCustomer() {
        return customer;
    }

    public State getState() {
        return state;
    }

    @Override
    public String toString() {
        return "PurchaseOrder{" + "id=" + getId() + ", date=" + date
                + ", items=" + items + ", customer=" + customer
                + ", state=" + state + '}';
    }
}

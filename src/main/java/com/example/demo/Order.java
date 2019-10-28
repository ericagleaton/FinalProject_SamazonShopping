package com.example.demo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity

@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long OrderId;
//    private double total_cost;
    private boolean orderStatus;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;
/*

    @ManyToMany (mappedBy = "orders")
    private Collection<Product> products;
*/


    @OneToMany(mappedBy = "order", cascade=CascadeType.ALL)
    private Collection<Product> products;

    public Order(){}

    public Order(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

    /*public double getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(double total_cost) {
        this.total_cost = total_cost;
    }*/


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Product> getProducts() {
        return products;
    }

    public void setProducts(Collection<Product> products) {
        this.products = products;
    }

    public long getOrderId() { return OrderId; }

    public void setOrderId(long orderId) { this.OrderId = orderId; }

    public boolean isOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(boolean orderStatus) {
        this.orderStatus = orderStatus;
    }

   /* public String calculateCost(){
        double total = 0.0;
        ArrayList<Product> products = new ArrayList<>(getProducts());
        for (Product prod : products){
            double price = Double.parseDouble(prod.getPrice());
            total += price;
        }
        return Double.toString(total);
    }

    public String productQuantity(){
        return Integer.toString(getProducts().size());
    }

    public String calculateShipping(){
        String costString = calculateCost();
        double cost = (Double.valueOf(costString)).doubleValue();
        double shippingCost = 0.0;
        if(cost < 50){
            shippingCost =  (cost * .05);
            return Double.toString(shippingCost);
        }
        return Double.toString(shippingCost);
    }

    public String costPlusShipping(){
       String shippingString = calculateShipping();
        double shipping = (Double.valueOf(shippingString)).doubleValue();
        String productString = calculateCost();
        double productCost = (Double.valueOf(productString)).doubleValue();

        return Double.toString(shipping + productCost);
    }
*/

}
/*
package com.example.demo;

import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String orderStatus;
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_product", joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "productid") })
    private Set<Product> products = new HashSet<>(0);

    public Order() {
    }

    public Order(long id, String orderStatus) {
        this.id = id;
        this.orderStatus = orderStatus;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "Order_status")
    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Column(name = "user")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    */
/*@ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(name = "order_product", joinColumns = { @JoinColumn(name = "id") },
            inverseJoinColumns = { @JoinColumn(name = "productid") })*//*

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
*/

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author walre888
 */
public class Sale implements Serializable {
 
    private String id;
    private String date;
    private String uri;
    private Customer customer;
    private Totals total;
    private List<SaleItem> items = new ArrayList<>(); //Array Of Items

    public Sale(String id, String saleDate, String uri, Customer customer, List<SaleItem> items) {
        this.id = id;
        this.date = saleDate;
        this.uri = uri;
        this.customer = customer;
        this.items = items;
    }
    
    public Sale() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSaleDate() {
        return date;
    }

    public void setSaleDate(String saleDate) {
        this.date = saleDate;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<SaleItem> getItems() {
        return items;
    }

    public void setItems(List<SaleItem> items) {
        this.items = items;
    }

    public Totals getTotal() {
        return total;
    }

    public void setTotal(Totals total) {
        this.total = total;
    }
    
    @Override
    public String toString() {
        return "Sale{" + "id=" + id + ", saleDate=" + date + ", uri=" + uri + ", customer=" + customer + '}';
    }
    
}

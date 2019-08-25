/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.io.Serializable;

/**
 *
 * @author walre888
 */
public class SaleItem implements Serializable {
    
    private String productID;
    private Double quantity;
    private Double price;

    public SaleItem() {
    }

    public SaleItem(String productID, Double quantity, Double price) {
        this.productID = productID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "SaleItem{" + "productID=" + productID + ", quantity=" + quantity + ", price=" + price + '}';
    }
}

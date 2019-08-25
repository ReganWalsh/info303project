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
public class Summary implements Serializable {
    
    private Integer numberOfSales;
    private Double totalPayment;
    public String group;
    public String uri;

    public Integer getNumberOfSales() {
        return numberOfSales;
    }

    public void setNumberOfSales(Integer numberOfSales) {
        this.numberOfSales = numberOfSales;
    }

    public Double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Summary() { //Summary Constructor, Providing Initial Values
        numberOfSales = 0;
        totalPayment = 0.0;
        group = "";
        uri = "";
    }

    public Summary(Integer numberOfSales, Double totalPayment, String group, String uri) {
        this.numberOfSales = numberOfSales;
        this.totalPayment = totalPayment;
        this.group = group;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "Summary{" + "numberOfSales=" + numberOfSales + ", totalPayment=" + totalPayment + ", group=" + group + ", uri=" + uri + '}';
    }
    
}

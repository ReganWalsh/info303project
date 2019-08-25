/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.CustomerAccount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author walre888
 */
public class CustomerDAO {
    
    private static final Map<String, CustomerAccount> cust = new TreeMap<>();
    
    static { //Test Objects
        if (cust.isEmpty()){
            cust.put("W1", new CustomerAccount("W1", "Regan", "Walsh", "ReganWalsh25@hotmail.com", "Dont Know", "http://localhost:8080/api/Customers/Customer/W1"));
            cust.put("W2", new CustomerAccount("W2", "Test", "Test", "Test@Test.Test", "Test", "http://localhost:8080/api/Customer/Customer/W2"));
        }
    }
    
    public ArrayList<CustomerAccount> getAllCustomers(){
        ArrayList list = new ArrayList(cust.values());
        return list;
    }
    
    public void saveCustomer (CustomerAccount customer){
        cust.put(customer.getId(), customer);
    }
    
    public CustomerAccount getCustomer(String id){
        return cust.get(id);
    }
    
    public void delete(String id){
        cust.remove(id);
    }
    
    public void update(String id, CustomerAccount changedCustomer){
        cust.put(id, changedCustomer);
    }
    
    public boolean ifExists(String id){
        return cust.containsKey(id);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replicator;

import domain.Customer;

/**
 *
 * @author ReganWalsh
 */
public class CustomerCreator {
       
    public Customer createCustomer(String id, String firstName, String lastName, String email, String group) { //Called To Create A Customer
            return new Customer(id, firstName, lastName, email, group);
    }
}


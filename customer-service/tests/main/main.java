/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

//import api.CustomersApi;
import domain.CustomerAccount;
import java.io.IOException;
import java.util.List;
// retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;

/**
 *
 * @author walre888
 */
public class main {
    
    public static void main(String[]args) throws IOException{
    
    //Retrofit retrofit = new Retrofit.Builder()
            //.baseUrl("http://localhost:8080/api/")
            //.addConverterFactory(GsonConverterFactory.create())
            //.build();
    
    //CustomersApi customersApi = retrofit.create(CustomersApi.class);
    //List<Customer> customers = customersApi.customersGet().execute().body();
    
    CustomerAccount customer = new CustomerAccount();
    customer.setId("W3");
    customer.setFirstName("Jim");
    customer.setLastName("Bob");
    customer.setEmail("Jim@Bob.com");
    customer.setGroup("Group");
    customer.setUri("http://localhost/api/Customers/Customer/W3");
    
    //customersApi.customersPost(customer);
    //customers.add(customer);
    //System.out.println(customers);
    
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.CustomerDAO;
import domain.CustomerAccount;
import org.jooby.*;
/**
 *
 * @author walre888
 */
public class CustomersResource extends Jooby {

    public CustomersResource(CustomerDAO dao) {

        path("/api/Customers", () -> { 
            get(() -> {
                return dao.getAllCustomers(); //Get All Customers
            });
            post((req, rsp) -> {
                CustomerAccount customer = req.body(CustomerAccount.class);
                String uri = "http://" + req.hostname() + ":" + req.port() + "" + req.path() + "/Customer/" + customer.getId(); //Create URI For Customer
                customer.setUri(uri);
                
                if(dao.ifExists(customer.getId())) {
                    rsp.status(Status.UNPROCESSABLE_ENTITY);
                } else {
                    dao.saveCustomer(customer); //Save Customer
                    rsp.status(Status.CREATED).send(customer);
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}

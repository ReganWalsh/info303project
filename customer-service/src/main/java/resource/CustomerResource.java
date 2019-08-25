/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.CustomerDAO;
import domain.CustomerAccount;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author walre888
 */
public class CustomerResource extends Jooby {

    public CustomerResource(CustomerDAO dao) {

        path("/api/Customers/Customer", () -> {

            get("/:id", (req) -> { //Get Single Customer Based On Provided ID
                String id = req.param("id").value();
                if (dao.ifExists(id)) { //If It Exists Get Customer
                    return dao.getCustomer(id);
                } else {
                    throw new Err(Status.NOT_FOUND, "No Customer With That ID");
                }
            });
            put("/:id", (req, rsp) -> { //Edit Customer Mased On Provided ID
                String id = req.param("id").value();
                CustomerAccount customer = req.body().to(CustomerAccount.class); //Parse Customer To CustomerAccount
                if (!dao.ifExists(id)) { //If It Doesnt Exist
                    rsp.status(Status.NOT_FOUND);
                } else if (!id.equals(customer.getId())) { 
                    rsp.status(Status.CONFLICT);
                } else {
                    dao.update(id, customer); //Update Customer
                    rsp.send(customer);
                }
            });
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}

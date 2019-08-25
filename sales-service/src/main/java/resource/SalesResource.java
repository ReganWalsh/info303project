/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.SalesDAO;
import domain.Sale;
import org.jooby.*;

/**
 *
 * @author walre888
 */
public class SalesResource extends Jooby {
    
    public SalesResource(SalesDAO dao) {
        
        path("api/Sales", () -> {
            get(() -> {
                return dao.getAllSales();
            });
            
            post((req, rsp) -> {
                Sale sale = req.body(Sale.class);
                //String uri = req.path();
                String uri = req.path() + "/Sale/" + sale.getId();
                sale.setUri(uri);
                
                if(dao.ifExists(sale.getId())) {
                    rsp.status(Status.UNPROCESSABLE_ENTITY);
                } else {
                    dao.saveSale(sale);
                    rsp.status(Status.CREATED).send(sale);
                }
                
            }); 
        }).produces(MediaType.json).consumes(MediaType.json);
    }
    
}




/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resource;

import dao.SalesDAO;
import domain.Customer;
import domain.Sale;
import org.jooby.Err;
import org.jooby.Jooby;
import org.jooby.MediaType;
import org.jooby.Status;

/**
 *
 * @author walre888
 */
public class SaleResource extends Jooby {

    public SaleResource(SalesDAO dao) {

        path("/api/Sales/Customer", () -> {

            get("/:id", (req) -> {
                String id = req.param("id").value();
                if (dao.ifExists(id)) {
                    return dao.getSalesByID(id);
                } else {
                    throw new Err(Status.NOT_FOUND, "No Sale With That ID");
                }
            });
            
            put("/:id", (req, rsp) -> {
                String id = req.param("id").value();
                Sale sale = req.body().to(Sale.class);
                if (!dao.ifExists(id)) {
                    rsp.status(Status.NOT_FOUND);
                } else if (!id.equals(sale.getId())) {
                    rsp.status(Status.CONFLICT);
                } else {
                    dao.update(id, sale);
                    rsp.send(sale);
                }
            });
            
            get("/:id/Summary", (req) -> {
                String id = req.param("id").value();
                if (dao.ifExists(id)) {
                    return dao.getSalesSummary(id);
                } else {
                    throw new Err(Status.NOT_FOUND, "No Sale With That ID");
                }
            });      
        }).produces(MediaType.json).consumes(MediaType.json);
    }
}

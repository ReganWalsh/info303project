/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import domain.Customer;
import domain.Sale;
import domain.SaleItem;
import domain.Summary;
import domain.Totals;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author walre888
 */
public class SalesDAO {

    private static final Multimap<String, Sale> sales = ArrayListMultimap.create();
    
    static {
        if (sales.isEmpty()) {
              
            Customer customer1 = new Customer("C1", "Regan", "Walsh", "walre888@student.otago.ac.nz");
            Customer customer2 = new Customer("C2", "Test", "Test", "Test@Test.Test");
       
            SaleItem item = new SaleItem("G1", 12.0, 2.50);         
            ArrayList list = new ArrayList();
            list.add(item);

            Sale finalSale1 = new Sale("W1", "24/3/2018", "http://localhost:8081/api/Sales/Sale/W1", customer1, list);
            Sale finalSale2 = new Sale("W2", "25/3/2018", "http://localhost:8081/api/Sales/Sale/W2", customer1, list);
            Sale finalSale3 = new Sale("W3", "26/3/2018", "http://localhost:8081/api/Sales/Sale/W3", customer2, list);

            sales.put(finalSale1.getCustomer().getId(), finalSale1);
            sales.put(finalSale2.getCustomer().getId(), finalSale2);
            sales.put(finalSale3.getCustomer().getId(), finalSale3);
        }
             
    }

    public List<Sale> getAllSales() {
        ArrayList list = new ArrayList(sales.values());
        return list;
    }

    public void saveSale(Sale sale) {
        sales.put(sale.getCustomer().getId(), sale);
    }

//    public Sale getSale(String id) {
//        return sales.get(id);
//    }

//    public void delete(String id) {
//        sales.remove(id);
//    }

    public void update(String id, Sale changedSale) {
        sales.put(id, changedSale);
    }

    public boolean ifExists(String id) {
        return sales.containsKey(id);
    }

    public Collection<Sale> getSalesByID(String id) {
//        ArrayList<Sale> salesByID = new ArrayList();
//        sales.forEach((key, sale) -> {
//            if (sale.getId().equals(id)) {
//                salesByID.add(sale);
//            }
//        });
//        return salesByID;
        return sales.get(id);
    }

    public Summary getSalesSummary(String id) {
        Collection<Sale> cSales = sales.get(id);
        Summary summary = new Summary();
        summary.setNumberOfSales(cSales.size());
             
        double total = 0;
                
        for (Sale sale : cSales) {
            String uri = "http://localhost:8081/api/Sales/Sale/" + sale.getCustomer().getId() + "/Summary";
            summary.setUri(uri);
            //summary.setUri(sale.getUri() + "/Summary");
            
            total += sale.getTotal().getTotalPayment();
        }

        summary.setTotalPayment(total);
       
        if (summary.getTotalPayment() >= 1000){
            summary.setGroup("VIP Customer");
        } else {
            summary.setGroup("Regular Customer");
        }
        return summary;
    }
}

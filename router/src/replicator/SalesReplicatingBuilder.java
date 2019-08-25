/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replicator;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import static org.apache.camel.model.dataformat.JsonLibrary.Gson;

/**
 *
 * @author walre888
 */
public class SalesReplicatingBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        from("imaps://outlook.office365.com?username=walre888@student.otago.ac.nz" //Pull From Student Email
                + "&password=" + getPassword("Enter your E-Mail password")
                + "&searchTerm.subject=Vend:SaleUpdate"
                + "&debugMode=false"
                + "&folderName=INBOX")
                //.convertBodyTo(String.class)
                .log("${body}")
                .to("jms:queue:vend-new-sale");
 
        from("jms:queue:vend-new-sale")
                .process(new Processor() {
                    
                    @Override
                    public void process(Exchange exchange) throws Exception { //Convert It Into Json Object
                        String message = exchange.getIn().getBody(String.class);
                        JsonObject vend = new Gson().fromJson(message, JsonObject.class);
                        JsonObject customer = vend.getAsJsonObject("customer");
                        JsonObject totals = vend.getAsJsonObject("totals");
                        
                        JsonObject items = new JsonObject();
                        JsonArray arrayListOfItems = new JsonArray();
                        items.addProperty("productID", "");
                        items.addProperty("quantity", 0.0);
                        items.addProperty("price", 0.0);
                        arrayListOfItems.add(items);
                        
                        JsonObject finalSale = new JsonObject();
                        finalSale.addProperty("id", vend.get("id").getAsString());
                        finalSale.addProperty("saleDate", vend.get("created_at").getAsString());
                        finalSale.addProperty("uri", "http://localhost:8081/Sales/Sale/" + vend.get("id").getAsString());
                      
                        JsonObject customer1 = new JsonObject();
                        customer1.addProperty("id", customer.get("id").getAsString());
                        customer1.addProperty("firstName", customer.get("first_name").getAsString());
                        customer1.addProperty("lastName", customer.get("last_name").getAsString());
                        customer1.addProperty("group", customer.get("customer_group_id").getAsString());
                        customer1.addProperty("email", "");
                        
                        JsonObject totals1 = new JsonObject();
                        totals1.addProperty("totalPrice", totals.get("total_price").getAsDouble());
                        totals1.addProperty("totalTax", totals.get("total_tax").getAsDouble());
                        totals1.addProperty("totalPayment", totals.get("total_payment").getAsDouble());
                        
                        finalSale.add("customer", customer1);
                        finalSale.add("total", totals1);
                        finalSale.add("items", arrayListOfItems);
                        
                        System.out.println(finalSale.toString());
                        exchange.getIn().setBody(finalSale.toString());
                    }
 
                })
                .log("${body}")
                .to("jms:queue:newsaledata"); //Send Sale Data To Object

        from("jms:queue:newsaledata")
                .removeHeaders("*")
                .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .to("http4://localhost:8081/api/Sales") //POST To Sale Service
                .to("jms:queue:extractedsale"); //And Alternative Camel Route
        
//        from("jms:queue:extractedsale")
//                .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
//                .setHeader(Exchange.HTTP_METHOD, constant("GET"))
//                .recipientList()
//                .simple("http://localhost:8081/api/Sale/Customer/${exchangeProperty}/Summary")
//                .to("jms:queue:summary");

//        from("jms:queue:extractedsale")
//                .process(new Processor() {
//                    @Override
//                    public void process(Exchange exchange) throws Exception {
//                        String message = exchange.getIn().getBody(String.class);
//                        JsonObject json = new Gson().fromJson(message, JsonObject.class);
//                        JsonObject customerObject = json.getAsJsonObject("customer");
//                        JsonObject totalObject = json.getAsJsonObject("total");
//                        JsonObject summaryObject = new JsonObject();
//                        JsonObject totalPriceObject = new JsonObject();
//                        summaryObject.addProperty("group", customerObject.get("group").getAsString());
//                        totalPriceObject.addProperty("totalPayment", totalObject.get("totalPayment").getAsDouble());
//                        
//                        if(customerObject.get("group").equals("0afa8de1-147c-11e8-edec-2b197906d816")) {
//                            
//                        }
//                        
//                    }
//        } );
    }

//        from("jms:queue:newsaledata")
//                .removeHeaders("*")
//                .marshal().json(JsonLibrary.Gson)
//                .setHeader(Exchange.CONTENT_TYPE).constant("application/json")
//                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//                .to("jms:queue:saledata"); 
 
    public static String getPassword(String prompt) { //Gets Password For Email
        JPasswordField txtPasswd = new JPasswordField();
        int resp = JOptionPane.showConfirmDialog(null, txtPasswd, prompt,
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (resp == JOptionPane.OK_OPTION) {
            String password = new String(txtPasswd.getPassword());
            return password;
        }
        return null;
    }
}
  

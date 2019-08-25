/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replicator;

import domain.Customer;
import domain.CustomerAccount;
import domain.Sale;
import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

/**
 *
 * @author ReganWalsh
 */
public class ReplicatingBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        
        from("jetty:http://localhost:9000/?enableCORS=true") 
                .setExchangePattern(ExchangePattern.InOnly)
                .log("${body}")
                .to("jms:queue:fromajax");
       
        from("jms:queue:fromajax")
                .unmarshal().json(JsonLibrary.Gson, CustomerAccount.class) //Unmarshal The Customer
                .to("jms:queue:customeraccount");
        
        from("jms:queue:customeraccount")
                .bean(CustomerCreator.class, "createCustomer(" //Split Into Single Types
                        //+ "${body.id}, "
                        + "${body.firstName}, "
                        + "${body.lastName}, "
                        + "${body.email}"
                        + "${body.group})") 
                .to("jms:queue:send-to-vend");
                
//        from("jms:queue:customer")
//                .marshal().json(JsonLibrary.Gson)
//                .removeHeaders("*") 
//                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
//                .setHeader(Exchange.CONTENT_TYPE, constant("application/json"))
//                .to("jms:queue:send-to-vend");
       
        from("jms:queue:send-to-vend")
                .removeHeaders("*")
                .setHeader("Authorization", constant("Bearer KiQSsELLtocyS2WDN5w5s_jYaBpXa0h2ex1mep1a")) //Send With Token
                .marshal().json(JsonLibrary.Gson) //Marshal To JSON
                .setHeader(Exchange.CONTENT_TYPE).constant("application/json") //Set Header Details
                .setHeader(Exchange.HTTP_METHOD, constant("POST"))
                .wireTap("jms:queue:wireTap")
                .to("https4://info303otago.vendhq.com/api/2.0/customers") //Post To API
                //.to("http4://localhost:9100/api/2.0/customers")
                .to("jms:queue:vend-response"); //And Also To Camel Route

    }
}

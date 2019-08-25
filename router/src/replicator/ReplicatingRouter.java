/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package replicator;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

/**
 *
 * @author ReganWalsh
 */
public class ReplicatingRouter {

    public static void main(String[] args) throws Exception {

        CamelContext camel = new DefaultCamelContext();

        ActiveMQConnectionFactory activeMqFactory = new ActiveMQConnectionFactory("tcp://localhost:61616"); //Create A New Connection
        JmsComponent jmsComponent = JmsComponent.jmsComponent(activeMqFactory);
        camel.addComponent("jms", jmsComponent);

        jmsComponent.setTransferExchange(false);

        activeMqFactory.setTrustAllPackages(true);

        camel.setTracing(false);

        camel.setStreamCaching(true);

        camel.addRoutes(new ReplicatingBuilder()); //Add RouteBuilders To Routet
        camel.addRoutes(new SalesReplicatingBuilder());

        System.out.println("Starting router...");
        camel.start();
        System.out.println("... ready.  Press enter to shutdown.");
        System.in.read();
        camel.stop();
    }

}

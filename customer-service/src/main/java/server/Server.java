/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import org.jooby.*;
import dao.CustomerDAO;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.apitool.ApiTool;
import org.jooby.handlers.Cors;
import org.jooby.handlers.CorsHandler;
import org.jooby.json.Gzon;
import resource.CustomerResource;
import resource.CustomersResource;

/**
 *
 * @author walre888
 */
public class Server extends Jooby {

    public Server() {

        CustomerDAO dao = new CustomerDAO();
        assets("swagger.json");
        assets("swagger.yaml");
        use("*", new CorsHandler(new Cors().withMethods("*")));        
        use(new Gzon());
        use(new CustomersResource(dao));
        use(new CustomerResource(dao)); 
        use(new ApiTool().swagger());
    }
    
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter To Stop The Service.....");
        });
        System.in.read();
        server.stop();
    }
}

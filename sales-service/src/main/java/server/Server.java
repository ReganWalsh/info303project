/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import dao.SalesDAO;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.apitool.ApiTool;
import org.jooby.json.Gzon;
import resource.SaleResource;
import resource.SalesResource;

/**
 *
 * @author walre888
 */
public class Server extends Jooby {
    
        public Server() {

        SalesDAO dao = new SalesDAO();
        assets("swagger.json");
        assets("swagger.yaml");
        use(new Gzon());
        use(new SalesResource(dao));
        use(new SaleResource(dao));
        use(new ApiTool().swagger());
        
    }
    
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.port(8081);
        CompletableFuture.runAsync(() -> {
            server.start();
        });
        server.onStarted(() -> {
            System.out.println("\nPress Enter To Stop The Service.....");
        });
        System.in.read();
        System.exit(0);
    }
    
}

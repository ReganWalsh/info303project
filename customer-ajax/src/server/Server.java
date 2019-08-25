package server;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import org.jooby.Jooby;
import org.jooby.Results;

public class Server extends Jooby {

	public Server() {

		assets("/**");
		assets("/", "index.html");
		get("/favicon.ico", () -> Results.noContent());

	}

	public static void main(String[] args) throws IOException {

		Server server = new Server(); //New Server Instance

		server.port(8081); //Select Port

		CompletableFuture.runAsync(() -> { //Run Server Asynchronously
			server.start();
		});

		server.onStarted(() -> { //Once Server Started 
			System.out.println("\nPress Enter to stop service.");
		});

		System.in.read(); //Reads Next Byte Of Data, i.e Enter To Stop Service 
		server.stop(); //Then Stop

	}

}

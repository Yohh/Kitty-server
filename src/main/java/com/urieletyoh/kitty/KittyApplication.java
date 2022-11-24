package com.urieletyoh.kitty;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KittyApplication {

	public static void main(String[] args) {
		SpringApplication.run(KittyApplication.class, args);

		Configuration config = new Configuration();
		config.setHostname("localhost");
		config.setPort(8085);
	//	config.setOrigin("http://localhost:8085");

		final SocketIOServer server = new SocketIOServer(config);

		server.addConnectListener(client -> System.out.println(" connection ok " + client.getSessionId()));

		server.addDisconnectListener(client -> System.out.println(" connection lost " + client.getSessionId()));

		server.addEventListener("chatevent", Message.class, new DataListener<Message>() {
			@Override
			public void onData(SocketIOClient client, Message data, AckRequest ackRequest) {
				// broadcast messages to all clients
				System.out.println(data.toString());
				server.getBroadcastOperations().sendEvent("chatevent", data);
			}
		});

		server.start();
	}

}

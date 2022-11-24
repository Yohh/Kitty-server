package com.urieletyoh.kitty.service;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.urieletyoh.kitty.Message;

public class ChatLauncher {

    static void main(String... args) throws Exception {

        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8085);

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

        Thread.sleep(Integer.MAX_VALUE);

        server.stop();

    }

    public void start() {
    }
}

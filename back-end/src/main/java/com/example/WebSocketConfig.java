package com.example;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // prefix used for server-to-client messages
        config.enableSimpleBroker("/topic/");
        // prefix used for client-to-server messages
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // endpoint path used for client to establish WebSocket connection with server
        // ws://ipaddr:port/demo-websocket
        registry.addEndpoint("/demo-websocket")
                .setAllowedOriginPatterns("*"); // accept cross-origin requests from any origin
    }
}
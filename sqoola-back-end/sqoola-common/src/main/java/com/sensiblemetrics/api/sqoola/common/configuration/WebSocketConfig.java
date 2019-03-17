package com.sensiblemetrics.api.sqoola.common.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.boraji.tutorial.spring.websocket.MyWebSocketHandler;

@Configuration
@EnableWebSocket
@ComponentScan("com.boraji.tutorial.spring.websocket")
public class WebSocketConfig implements WebSocketConfigurer {

   @Autowired
   private MyWebSocketHandler myWebSocketHandler;

   @Override
   public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
      registry.addHandler(myWebSocketHandler, "/socketHandler");
   }

}

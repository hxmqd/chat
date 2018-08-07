package com.hxm.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurationSupport;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;

import com.hxm.chat.data.ChatUserData;

@Configuration
public class WebSocketConfig extends WebSocketMessageBrokerConfigurationSupport {
	
	public static SimpMessagingTemplate messagingTemplate;
	@Override
	protected void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		// TODO Auto-generated method stub
		registry.addDecoratorFactory(new WebSocketHandlerDecoratorFactory() {
			@Override
			public WebSocketHandler decorate(WebSocketHandler handler) {
				// TODO Auto-generated method stub
				 return new WebSocketHandlerDecorator(handler) {
					 public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
				            // 客户端与服务器端建立连接后，此处记录谁上线了
						 String username = session.getPrincipal().getName();
				         ChatUserData.addOnLineUser(username);
				 		messagingTemplate.convertAndSend("/topic", "up|"+username +'|'+ "上线了"); 
				         super.afterConnectionEstablished(session);
				          }
				          @Override
				          public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
				            // 客户端与服务器端断开连接后，此处记录谁下线了
				            String username = session.getPrincipal().getName();
				        	ChatUserData.removeOnLineUser(username);
				        	messagingTemplate.convertAndSend("/topic", "down|"+username +'|'+ "下线了"); 
				            super.afterConnectionClosed(session, closeStatus);
				          }

				 };
			}
			
		});
		     
		      
		super.configureWebSocketTransport(registry);
	}

	@Override
	protected void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		registry.addEndpoint("/websocket").withSockJS();
		
	}

	@Override
	protected void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		registry.enableSimpleBroker("/chat","/topic");	}
	

}

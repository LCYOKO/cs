package com.xiaomi.cs.config;

import com.xiaomi.cs.handler.CustomerHandler;
import com.xiaomi.cs.handler.SupportHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServletServerContainerFactoryBean;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * @author l
 * @create 2020-10-28-20:22
 */
@EnableWebSocket
@Component
public class WebSocketConfig implements  WebSocketConfigurer{

    @Autowired
    private CustomerHandler customerHandler;
    @Autowired
    private SupportHandler supportHandler;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(customerHandler, "/webSocket/customer").
                addHandler(supportHandler,"/webSocket/support").
                setAllowedOrigins("*");
    }

    @Bean
    public ServletServerContainerFactoryBean createServletServerContainerFactoryBean() {
        ServletServerContainerFactoryBean container = new ServletServerContainerFactoryBean();
        container.setMaxTextMessageBufferSize(1024*1024*3);
        container.setMaxBinaryMessageBufferSize(1024*1024*3);
        container.setMaxSessionIdleTimeout(1000 * 60 * 3L);
        return container;
    }
}



//@EnableWebSocketMessageBroker
////@Component
////public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
////
////    @Override
////    public void registerStompEndpoints(StompEndpointRegistry registry) {
////        // 配置客户端尝试连接地址
////        registry.addEndpoint("/ws").setAllowedOrigins("*").withSockJS();
////    }
////
////    @Override
////    public void configureMessageBroker(MessageBrokerRegistry registry) {
////        // 设置广播节点
////        registry.enableSimpleBroker("/topic", "/user");
////        // 客户端向服务端发送消息需有/app 前缀
////        registry.setApplicationDestinationPrefixes("/app");
////        // 指定用户发送（一对一）的前缀 /user/
////        registry.setUserDestinationPrefix("/user/");
////    }
////
////}

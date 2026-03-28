package com.demo.webflux.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.reactive.function.server.ServerRequest;

import com.demo.webflux.handler.CustomerHandler;
import com.demo.webflux.handler.CustomerStreamHandler;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler handler;

    @Autowired
    private CustomerStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routeFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", handler::loadCustomers)
                .GET("/router/customers/stream", streamHandler::GetCustomer)
                .GET("router/customer/{input}", handler::findCustomer)
                .POST("/router/customer/save", this::saveCustomer, handler::saveCustomer)
                .build();
        
    }
}

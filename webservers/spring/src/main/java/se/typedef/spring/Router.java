package se.typedef.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;

@Configuration
public class Router {

  @Bean
  public RouterFunction<ServerResponse> route(final DemoHandler demoHandler) {
    return RouterFunctions.route(GET("/get"), demoHandler::get)
        .andRoute(POST("/post"), demoHandler::postWithData);
  }
}

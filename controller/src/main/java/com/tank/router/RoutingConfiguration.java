package com.tank.router;

import com.tank.controller.PersonController;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.tank.constants.UrlPrefix.URL_PREFIX;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
@EnableWebFlux
public class RoutingConfiguration {

  @Bean
  public RouterFunction<ServerResponse> personRouter() {

    val findPersonWithIdRouter = GET(URL_PREFIX + "/{id}/person").and(JSON_FORMATTER);

    return RouterFunctions.route(findPersonWithIdRouter, personController::findBy);
  }

  private final RequestPredicate JSON_FORMATTER = accept(MediaType.APPLICATION_JSON_UTF8);

  @Autowired
  private PersonController personController;
}

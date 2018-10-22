package com.tank.router;

import com.tank.handler.PersonHandler;
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
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@EnableWebFlux
public class RoutingConfiguration {

  @Bean
  public RouterFunction<ServerResponse> personRouter() {

    val pattern = URL_PREFIX;
    val findPersonWithIdRouter = GET(pattern + "/{id}/person").and(JSON_FORMATTER);
    val createPersonRouter = POST(pattern + "/person/create").and(JSON_FORMATTER);
    val listPersons = GET(pattern + "/persons").and(JSON_FORMATTER);
    val updatePerson = PUT(pattern + "/{id}/person").and(JSON_FORMATTER);
    val userNumRouter = GET(pattern + "/users/num").and(JSON_FORMATTER);
    val userNameRouter = GET(pattern + "/user/{name}").and(JSON_FORMATTER);

    return RouterFunctions.
        route(findPersonWithIdRouter, personHandler::findBy)
        .andRoute(createPersonRouter, personHandler::create)
        .andRoute(listPersons, personHandler::list)
        .andRoute(updatePerson, personHandler::update)
        .andRoute(userNumRouter, personHandler::num)
        .andRoute(userNameRouter, personHandler::demo);
  }

  private final RequestPredicate JSON_FORMATTER = accept(MediaType.APPLICATION_JSON_UTF8);

  @Autowired
  private PersonHandler personHandler;
}

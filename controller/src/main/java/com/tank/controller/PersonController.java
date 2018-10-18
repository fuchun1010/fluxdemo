package com.tank.controller;

import com.tank.entity.Person;
import com.tank.service.PersonService;
import com.tank.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author fuchun
 */
@Slf4j
@Component
public class PersonController {

  @CrossOrigin
  public Mono<ServerResponse> findBy(ServerRequest request) {
    return this.personService.findBy(request.pathVariable("id"))
        .flatMap(p -> this.responseWrapper.responseMono(p, Person.class));
  }

  @CrossOrigin
  public Mono<ServerResponse> create(final ServerRequest request) {

    return this.personService
        .savePerson(request.bodyToMono(Person.class))
        .flatMap(p -> {
          Map<String, String> resp = new HashMap<>(8);
          resp.put("id", p.getId());
          Mono<ServerResponse> response = this.responseWrapper.<Map<String, String>>responseMono(resp, Map.class);
          return response;
        }).doOnError(err -> log.error(err.getLocalizedMessage()));
  }

  @CrossOrigin
  public Mono<ServerResponse> list(final ServerRequest request) {
    return this.personService.list()
        .collectList()
        .map(list -> Collections.singletonMap("persons", list))
        .flatMap(map -> {
          Mono<ServerResponse> response = this.responseWrapper.<Map<String, List<Person>>>responseMono(map, Map.class);
          return response;
        })
        .doOnError(err -> log.error(err.getLocalizedMessage()));
  }

  @Autowired
  private ResponseWrapper responseWrapper;

  @Autowired
  private PersonService personService;


}

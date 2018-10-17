package com.tank.controller;

import com.tank.entity.Person;
import com.tank.service.PersonService;
import com.tank.wrapper.ResponseWrapper;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fuchun
 */
@Slf4j
@Component
public class PersonController {

  @CrossOrigin
  public Mono<ServerResponse> findBy(ServerRequest request) {
    val id = request.pathVariable("id");
    val service = new PersonService();
    try {
      val person = service.findBy(id);
      return this.responseWrapper.<Person>responseData(person);
    } catch (Exception ex) {
      val errors = new HashMap<String, String>(8);
      errors.putIfAbsent("error", ex.getLocalizedMessage());
      return this.responseWrapper.<Map>responseErrorWithMessage(errors);
    }
  }

  @CrossOrigin
  public Mono<ServerResponse> create(ServerRequest request) {

    request.bodyToMono(Person.class).doOnNext(person -> {
      System.out.println("create person here");
      System.out.println(person.getGender());
    }).doOnError(err -> {
      log.error("error", err.getLocalizedMessage());
    }).toFuture().join();

    System.out.println("end create person");
    return this.responseWrapper.created();
  }

  @Autowired
  private ResponseWrapper responseWrapper;

}

package com.tank.controller;

import com.tank.entity.Person;
import com.tank.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static com.tank.constants.UrlPrefix.URL_PREFIX;

@RestController
@RequestMapping(URL_PREFIX)
@CrossOrigin
public class UserController {

  @GetMapping(path = "/users", produces = "application/stream+json")
  public Flux<Person> users() {

    return this.personService.list();
  }

  @Autowired
  private PersonService personService;
}

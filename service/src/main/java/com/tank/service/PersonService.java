package com.tank.service;

import com.tank.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class PersonService {

  public  Mono<Person> savePerson(Mono<Person> person) {
    return this.template.save(person);
  }

  @Autowired
  private ReactiveMongoTemplate template;
}

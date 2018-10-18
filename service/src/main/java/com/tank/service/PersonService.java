package com.tank.service;

import com.tank.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author fuchun
 */
@Slf4j
@Component
public class PersonService {

  public Mono<Person> savePerson(Mono<Person> person) {
    return this.template.save(person);
  }

  public Mono<Person> findBy(final String id) {
    return this.template.findById(id, Person.class);
  }

  public Flux<Person> list() {
    return this.template.findAll(Person.class);
  }

  @Autowired
  private ReactiveMongoTemplate template;
}

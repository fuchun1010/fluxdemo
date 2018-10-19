package com.tank.service;

import com.tank.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

  public Mono<Person> update(final String id, Mono<Person> newPerson) {
    return newPerson.flatMap(n -> {
      Query query = new Query();
      query.addCriteria(Criteria.where("id").is(id));
      return this.template.findOne(query, Person.class).flatMap(p -> {
        p.setName(n.getName());
        p.setGender(n.getGender());
        return this.template.save(p);
      });
    });

  }

  @Autowired
  private ReactiveMongoTemplate template;
}

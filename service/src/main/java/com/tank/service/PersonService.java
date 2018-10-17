package com.tank.service;

import com.tank.entity.Person;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PersonService {

  public Person findBy(@NonNull final String id) {
    return new Person().setId(id).setName("lisi").setGender("male");
  }
}

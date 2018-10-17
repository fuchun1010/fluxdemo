package com.tank.controller;

import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.HashMap;

import static com.tank.constants.UrlPrefix.URL_PREFIX;

@RestController
@RequestMapping(URL_PREFIX)
@CrossOrigin
public class UserController {

  @GetMapping("/user/hello")
  public Mono<ResponseEntity> sayHello() {

    val response = new HashMap<String, String>(8);
    response.putIfAbsent("word", "welcome to flux");
    return Mono.just(ResponseEntity.ok(response));

  }
}

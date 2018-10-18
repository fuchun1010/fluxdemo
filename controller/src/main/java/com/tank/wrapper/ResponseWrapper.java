package com.tank.wrapper;


import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @param <T>
 * @author fuchun
 */
@Component
public class ResponseWrapper<T> {


  public Mono<ServerResponse> responseMono(@NonNull final T body, Class<T> clazz) {
    return ServerResponse.ok()
        .body(Mono.just(body), clazz)
        .switchIfEmpty(ServerResponse.noContent().build());
  }


  public Mono<ServerResponse> created() {
    return ServerResponse.status(CREATED).build();
  }
}

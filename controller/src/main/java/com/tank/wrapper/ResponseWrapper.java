package com.tank.wrapper;


import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpStatus.CREATED;

/**
 * @param <T>
 * @author fuchun
 */
@Component
public class ResponseWrapper<T> {


  public Mono<ServerResponse> responseData(@NonNull final T body, Class<T> clazz) {
    return ServerResponse.ok()
        .body(Mono.just(body), clazz)
        .switchIfEmpty(ServerResponse.noContent().build());
  }

  public Mono<ServerResponse> created() {
    return ServerResponse.status(CREATED).build();
  }
}

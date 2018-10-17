package com.tank.wrapper;


import lombok.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;

/**
 * @param <T>
 * @author fuchun
 */
@Component
public class ResponseWrapper<T> {

  public Mono<ServerResponse> responseData(@NonNull final T body) {
    return ServerResponse.ok().contentType(APPLICATION_JSON_UTF8).body(fromObject(body));
  }

  public Mono<ServerResponse> responseErrorWithMessage(final Map<String, String> errors) {
    return ServerResponse
        .status(INTERNAL_SERVER_ERROR)
        .contentType(APPLICATION_JSON_UTF8)
        .body(fromObject(errors));
  }
}

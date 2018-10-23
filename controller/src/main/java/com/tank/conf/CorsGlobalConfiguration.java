package com.tank.conf;

import com.tank.constants.UrlPrefix;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author fuchun
 * global CrossOrigin
 */
@Configuration
@EnableWebFlux
public class CorsGlobalConfiguration implements WebFluxConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping(UrlPrefix.URL_PREFIX + "/**")
        .allowedOrigins("*")
        .allowedMethods("*");
  }

}

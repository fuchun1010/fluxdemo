package com.tank.conf;

import com.mongodb.MongoClientOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author fuchun
 */
@Configuration
public class MongoDbSettings {

  @Bean
  public MongoClientOptions mongoOptions() {
    return MongoClientOptions.builder()
        .socketTimeout(3000)
        .heartbeatSocketTimeout(3000)
        .minHeartbeatFrequency(25)
        .build();
  }
}

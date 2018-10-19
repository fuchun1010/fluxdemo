package com.tank.service;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;

@Component
public class HouseService {

  public Mono<Integer> count() {
    val sql = "select count(*) as cnt from tab_house ";
    return Mono.fromCallable(() -> this.template.queryForObject(sql, Integer.class)).publishOn(this.jdbcScheduler);
  }

  @Autowired
  @Qualifier("prestoJdbcTemplate")
  private JdbcTemplate template;

  @Autowired
  @Qualifier("jdbcScheduler")
  private Scheduler jdbcScheduler;
}

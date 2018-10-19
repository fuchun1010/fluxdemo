package com.tank.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.facebook.presto.jdbc.internal.guava.util.concurrent.ThreadFactoryBuilder;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import javax.sql.DataSource;
import java.util.concurrent.*;

/**
 * @author fuchun
 */
@Configuration
public class JdbcSchedulerConf {

  @Bean
  public Scheduler jdbcScheduler() {
    ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("jdbc-async-thread-%d").build();
    ExecutorService executorService = new ThreadPoolExecutor(
        this.corePoolSize,
        this.maxJdbcPool,
        this.keepAliveTime,
        TimeUnit.MILLISECONDS,
        new LinkedBlockingQueue<Runnable>(maxWaiting),
        factory,
        new ThreadPoolExecutor.AbortPolicy());

    return Schedulers.fromExecutor(Executors.newFixedThreadPool(this.maxJdbcPool));
  }


  @Bean(name = "prestoDataSource")
  public DataSource dataSource() {
    DruidDataSource dataSource = new DruidDataSource();
    val prefix = "spring.jdbc.presto.";
    dataSource.setUrl(env.getProperty(prefix + "url"));
    dataSource.setUsername(env.getProperty(prefix + "username"));
    dataSource.setPassword(env.getProperty(prefix + "password"));
    dataSource.setDriverClassName(env.getProperty(prefix + "driver-class-name"));
    dataSource.setValidationQuery(env.getProperty(prefix + "validation-query"));
    return dataSource;
  }

  @Bean(name = "prestoJdbcTemplate")
  public JdbcTemplate prestoJdbcTemplate(@Qualifier("prestoDataSource") DataSource dataSource) {
    return new JdbcTemplate(dataSource);
  }

  @Value("${spring.jdbc.maxThreadPool}")
  private int maxJdbcPool;

  @Value("${spring.jdbc.maxWaiting}")
  private int maxWaiting = 1024;

  @Value("${spring.jdbc.corePoolSize}")
  private int corePoolSize;

  @Value("${spring.jdbc.keepAliveTime}")
  private int keepAliveTime;

  @Value("${spring.jdbc.presto.url}")
  private String prestoUrl;

  @Autowired
  private Environment env;
}

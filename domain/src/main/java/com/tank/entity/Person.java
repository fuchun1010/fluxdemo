package com.tank.entity;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author fuchun
 */
@Data
@Accessors(chain = true)
@Document(collection = "tab_persons")
public class Person {

  @Id
  private String id;

  @Field("name")
  private String name;

  @Field("gender")
  private String gender;
}

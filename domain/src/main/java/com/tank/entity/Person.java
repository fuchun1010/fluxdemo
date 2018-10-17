package com.tank.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fuchun
 */
@Data
@Accessors(chain = true)
public class Person {

  private String id;

  private String name;

  private String gender;
}

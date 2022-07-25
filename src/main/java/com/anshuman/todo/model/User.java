package com.anshuman.todo.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document("users")
public class User {

  @Id
  private String id;

  @NotNull
  @Indexed(unique = true)
  private String email;

  @NotNull
  private String name;

  @NotNull
  private String avatar;

  public User() {
  }

  public User(String email, String name, String avatar) {
    this.email = email;
    this.name = name;
    this.avatar = avatar;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAvatar() {
    return avatar;
  }

  public void setAvatar(String avatar) {
    this.avatar = avatar;
  }
}

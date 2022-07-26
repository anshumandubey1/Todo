package com.anshuman.todo.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collection;


@Document("users")
public class User {

  @Id
  private String id;

  @NotNull
  @Indexed(unique = true)
  private String email;

  @NotNull
  private String name;
  private String password;

  @NotNull
  private String avatar;

  private Collection<GrantedAuthority> grantedAuthoritiesList = new ArrayList<>();

  @CreatedDate
  private Instant createdAt;

  @LastModifiedDate
  private Instant updatedAt;

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

  public Collection<GrantedAuthority> getGrantedAuthoritiesList() {
    return grantedAuthoritiesList;
  }

  public void setGrantedAuthoritiesList(Collection<GrantedAuthority> grantedAuthoritiesList) {
    this.grantedAuthoritiesList = grantedAuthoritiesList;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}

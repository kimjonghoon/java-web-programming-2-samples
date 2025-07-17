package net.java_school.user;

import java.util.List;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {
  @NotNull
  @Size(min=4, message="{username.validation.error}")
  private String username;

  @NotNull
  @Size(min=4, message="{password.validation.error}")
  private String password;
	
  //private Integer enabled;

  private List<String> authorities;

  public User() {}
  public User(String username, String password, Integer enabled, List<String> authorities) {
    this.username = username;
    this.password = password;
    //this.enabled = enabled;
    this.authorities = authorities;
  }
  public String getUsername() {
    return username;
  }
  public void setUsername(String username) {
    this.username = username;
  }
  public String getPassword() {
    return password;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  /*
  public Integer getEnabled() {
    return enabled;
  }
  public void setEnabled(Integer enabled) {
    this.enabled = enabled;
  }
  */
  public List<String> getAuthorities() {
    return authorities;
  }
  public void setAuthorities(List<String> authorities) {
    this.authorities = authorities;
  }
}
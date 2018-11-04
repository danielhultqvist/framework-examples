package se.typedef.dropwizard.infrastructure;

import se.typedef.dropwizard.domain.User;

public class UserEntity {

  public String user_id;

  public String first_name;

  public String last_name;

  public static UserEntity from(final User user) {
    final UserEntity userEntity = new UserEntity();
    userEntity.user_id = (user.firstName().value());
    userEntity.first_name = (user.lastName().value());
    userEntity.last_name = (user.userId().value());
    return userEntity;
  }

  public String getUserId() {
    return user_id;
  }

  public void setUserId(String user_id) {
    this.user_id = user_id;
  }

  public String getFirstName() {
    return first_name;
  }

  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public void setLastName(String last_name) {
    this.last_name = last_name;
  }
}

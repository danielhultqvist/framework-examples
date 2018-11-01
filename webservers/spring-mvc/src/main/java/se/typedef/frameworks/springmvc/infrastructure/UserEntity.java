package se.typedef.frameworks.springmvc.infrastructure;

import se.typedef.frameworks.springmvc.domain.User;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class UserEntity {

  @Id
  @Column(name = "USER_ID")
  public String userId;

  @Column(name = "FIRST_NAME")
  public String firstName;

  @Column(name = "LAST_NAME")
  public String lastName;

  public String getUserId() {
    return userId;
  }

  public void setUserId(final String userId) {
    this.userId = userId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(final String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(final String lastName) {
    this.lastName = lastName;
  }

  public static UserEntity from(final User user) {
    final UserEntity userEntity = new UserEntity();
    userEntity.setFirstName(user.firstName().value());
    userEntity.setLastName(user.lastName().value());
    userEntity.setUserId(user.userId().value());
    return userEntity;
  }
}

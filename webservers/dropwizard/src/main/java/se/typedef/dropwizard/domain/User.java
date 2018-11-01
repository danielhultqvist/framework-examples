package se.typedef.dropwizard.domain;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class User {
  public abstract FirstName firstName();

  public abstract LastName lastName();

  public abstract UserId userId();

  public static User create(
      final FirstName firstName, final LastName lastName, final UserId userId) {
    return new AutoValue_User(firstName, lastName, userId);
  }
}

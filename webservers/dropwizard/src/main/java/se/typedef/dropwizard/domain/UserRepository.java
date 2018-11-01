package se.typedef.dropwizard.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

  List<User> getAllUsers();

  Optional<User> getUser(UserId userId);

  void saveUser(User user);
}

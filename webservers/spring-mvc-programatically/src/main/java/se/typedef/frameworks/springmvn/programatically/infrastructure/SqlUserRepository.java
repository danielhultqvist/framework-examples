package se.typedef.frameworks.springmvn.programatically.infrastructure;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import se.typedef.frameworks.springmvn.programatically.domain.FirstName;
import se.typedef.frameworks.springmvn.programatically.domain.LastName;
import se.typedef.frameworks.springmvn.programatically.domain.User;
import se.typedef.frameworks.springmvn.programatically.domain.UserId;
import se.typedef.frameworks.springmvn.programatically.domain.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class SqlUserRepository implements UserRepository {

  private final Jdbi jdbi;

  public SqlUserRepository(final DataSource dataSource) {
    this.jdbi = Jdbi.create(dataSource);
  }

  @Override
  public List<User> getAllUsers() {
    return jdbi.withHandle(
        handle -> {
          handle.registerRowMapper(BeanMapper.factory(UserEntity.class));

          return handle
              .createQuery("SELECT * FROM user")
              .mapTo(UserEntity.class)
              .list()
              .stream()
              .map(
                  user ->
                      User.create(
                          FirstName.create(user.first_name),
                          LastName.create(user.last_name),
                          UserId.create(user.user_id)))
              .collect(toList());
        });
  }

  @Override
  public Optional<User> getUser(final UserId userId) {
    return jdbi.withHandle(
        handle -> {
          handle.registerRowMapper(BeanMapper.factory(UserEntity.class));

          return handle
              .createQuery("SELECT * FROM user WHERE user_id = :id")
              .bind("id", userId.value())
              .mapTo(UserEntity.class)
              .findFirst()
              .map(
                  user ->
                      User.create(
                          FirstName.create(user.first_name),
                          LastName.create(user.last_name),
                          UserId.create(user.user_id)));
        });
  }

  @Override
  public void saveUser(final User user) {
    final UserEntity userEntity = UserEntity.from(user);
    jdbi.useHandle(
        handle ->
            handle
                .createUpdate(
                    "INSERT INTO USER(first_name, last_name, user_id) values (:first_name, :last_name, :user_id)")
                .bindFields(userEntity)
                .execute());
  }
}

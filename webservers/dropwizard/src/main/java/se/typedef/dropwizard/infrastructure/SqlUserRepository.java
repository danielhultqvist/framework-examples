package se.typedef.dropwizard.infrastructure;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;
import se.typedef.dropwizard.domain.FirstName;
import se.typedef.dropwizard.domain.LastName;
import se.typedef.dropwizard.domain.User;
import se.typedef.dropwizard.domain.UserId;
import se.typedef.dropwizard.domain.UserRepository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class SqlUserRepository implements UserRepository {

  private final Jdbi jdbi;

  public SqlUserRepository(final Jdbi jdbi) {
    this.jdbi = jdbi;
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

package se.typedef.frameworks.springmvc.infrastructure;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import se.typedef.frameworks.springmvc.domain.FirstName;
import se.typedef.frameworks.springmvc.domain.LastName;
import se.typedef.frameworks.springmvc.domain.User;
import se.typedef.frameworks.springmvc.domain.UserId;
import se.typedef.frameworks.springmvc.domain.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Repository
@Transactional(readOnly = true)
public class SqlUserRepository implements UserRepository {

  @PersistenceContext private EntityManager entityManager;

  @Override
  public List<User> getAllUsers() {
    return entityManager
        .createQuery("SELECT u FROM UserEntity u", UserEntity.class)
        .getResultList()
        .stream()
        .map(
            user ->
                User.create(
                    FirstName.create(user.firstName),
                    LastName.create(user.lastName),
                    UserId.create(user.userId)))
        .collect(toList());
  }

  @Override
  public Optional<User> getUser(final UserId userId) {
    final UserEntity userEntity = entityManager.find(UserEntity.class, userId.value());

    return Optional.ofNullable(userEntity)
        .map(
            user ->
                User.create(
                    FirstName.create(user.firstName),
                    LastName.create(user.lastName),
                    UserId.create(user.userId)));
  }

  @Override
  @Transactional
  public void saveUser(final User user) {
    entityManager.persist(UserEntity.from(user));
  }
}

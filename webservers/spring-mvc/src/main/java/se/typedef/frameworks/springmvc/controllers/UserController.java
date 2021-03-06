package se.typedef.frameworks.springmvc.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.typedef.frameworks.springmvc.controllers.models.UserDTO;
import se.typedef.frameworks.springmvc.domain.FirstName;
import se.typedef.frameworks.springmvc.domain.LastName;
import se.typedef.frameworks.springmvc.domain.User;
import se.typedef.frameworks.springmvc.domain.UserId;
import se.typedef.frameworks.springmvc.domain.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
public class UserController {

  private final UserRepository userRepository;

  public UserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<UserDTO> getAllUsers() {
    return userRepository.getAllUsers().stream().map(UserDTO::new).collect(toList());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<UserDTO> getUser(@PathVariable("id") final String userId) {
    final Optional<UserDTO> user = userRepository.getUser(UserId.create(userId)).map(UserDTO::new);
    return ResponseEntity.of(user);
  }

  @PostMapping("/user")
  public ResponseEntity<Void> saveUser(@RequestBody final UserDTO userDTO) {
    userRepository.saveUser(
        User.create(
            FirstName.create(userDTO.firstName),
            LastName.create(userDTO.lastName),
            UserId.create(userDTO.userId)));

    return ResponseEntity.ok().build();
  }
}

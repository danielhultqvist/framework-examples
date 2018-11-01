package se.typedef.dropwizard.controllers;

import se.typedef.dropwizard.controllers.models.UserDTO;
import se.typedef.dropwizard.domain.UserId;
import se.typedef.dropwizard.domain.UserRepository;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Produces(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserRepository userRepository;

    public UserController(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GET
    @Path("/users")
    public List<UserDTO> getAllUsers() {
        return new ArrayList<>();
//        return userRepository.getAllUsers().stream().map(UserDTO::new).collect(toList());
    }

//    @GET
//    @Path("/user/{id}")
//    public ResponseEntity<UserDTO> getUser(@PathParam("username") final String userId) {
//        final Optional<UserDTO> user = userRepository.getUser(UserId.create(userId)).map(UserDTO::new);
//        return ResponseEntity.of(user);
//    }
        /*
         private final UserRepository userRepository;

  public UserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<UserDTO> getAllUsers() {
    return userRepository.getAllUsers().stream().map(UserDTO::new).collect(Collectors.toList());
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
         */

}

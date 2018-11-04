package se.typedef.dropwizard.controllers;

import se.typedef.dropwizard.controllers.models.UserDTO;
import se.typedef.dropwizard.domain.FirstName;
import se.typedef.dropwizard.domain.LastName;
import se.typedef.dropwizard.domain.User;
import se.typedef.dropwizard.domain.UserId;
import se.typedef.dropwizard.domain.UserRepository;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

@Produces(MediaType.APPLICATION_JSON)
@Path("/")
public class UserController {

  private final UserRepository userRepository;

  public UserController(final UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GET
  @Path("/users")
  public List<UserDTO> getAllUsers() {
    return userRepository.getAllUsers().stream().map(UserDTO::new).collect(toList());
  }

  @GET
  @Path("/user/{id}")
  public Response getUser(@PathParam("id") final String userId) {
    final Optional<UserDTO> user = userRepository.getUser(UserId.create(userId)).map(UserDTO::new);
    return user.map(u -> Response.ok(u).build()).orElse(Response.status(NOT_FOUND).build());
  }

  @POST
  @Path("/user")
  public Response saveUser(final UserDTO userDTO) {
    userRepository.saveUser(
        User.create(
            FirstName.create(userDTO.firstName),
            LastName.create(userDTO.lastName),
            UserId.create(userDTO.userId)));

    return Response.ok().build();
  }
}

package se.typedef.frameworks.springmvn.programatically.controllers.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;
import se.typedef.frameworks.springmvn.programatically.domain.User;

public class UserDTO {

    @JsonProperty("firstName")
    public String firstName;

    @JsonProperty("lastName")
    public String lastName;

    @JsonProperty("userId")
    public String userId;

    @JsonCreator
    public UserDTO(@JsonProperty("firstName") final String firstName,
                   @JsonProperty("lastName") final String lastName,
                   @JsonProperty("userId") final String userId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userId = userId;
    }

    public UserDTO(final User user) {
        Assert.notNull(user, "Required user is missing");
        this.firstName = user.firstName().value();
        this.lastName = user.lastName().value();
        this.userId = user.userId().value();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

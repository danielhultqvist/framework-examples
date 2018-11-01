package se.typedef.dropwizard;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Configuration;
import com.yammer.dropwizard.config.Environment;
import org.hibernate.validator.constraints.NotEmpty;
import se.typedef.dropwizard.controllers.UserController;

public class Application extends Service<AppConfiguration> {

  public static void main(String[] args) throws Exception {
    new Application().run(new String[]{"server", "webservers/dropwizard/src/main/resources/application.yaml"});
  }

  @Override
  public void initialize(Bootstrap<AppConfiguration> bootstrap) {
    bootstrap.setName("my-app-name");
  }

  @Override
  public void run(AppConfiguration configuration, Environment environment) {
    environment.addResource(new UserController(null));
  }
}

class AppConfiguration extends Configuration {
  @NotEmpty
  @JsonProperty
  String name;
}

package se.typedef.dropwizard;

import io.dropwizard.Application;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import se.typedef.dropwizard.controllers.UserController;
import se.typedef.dropwizard.infrastructure.SqlUserRepository;

public class Main extends Application<AppConfiguration> {

  public static void main(String[] args) throws Exception {
    final Main main = new Main();
    if (args.length == 0) {
      main.run("server", "webservers/dropwizard/src/main/resources/application.yaml");
    } else {
      main.run(args);
    }
  }

  @Override
  public void run(AppConfiguration configuration, Environment environment) {
    final Jdbi database = database(environment, configuration);
    final SqlUserRepository userRepository = new SqlUserRepository(database);
    final UserController userController = new UserController(userRepository);

    environment.jersey().register(userController);
  }

  private static Jdbi database(
      final Environment environment, final AppConfiguration configuration) {
    final JdbiFactory factory = new JdbiFactory();
    return factory.build(environment, configuration.getDataSourceFactory(), "mysql");
  }
}

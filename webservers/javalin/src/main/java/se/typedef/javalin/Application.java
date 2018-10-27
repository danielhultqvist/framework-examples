package se.typedef.javalin;

import io.javalin.Javalin;

public class Application {

  private static final int PORT = 8081;

  public static void main(String[] args) {
    final Javalin app = Javalin.create();
    final DependentServiceA dependentServiceA = new DependentServiceA();
    final Routes routes = new Routes(dependentServiceA);

    routes.register(app, dependentServiceA);

    app.start(PORT);
  }
}

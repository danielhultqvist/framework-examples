package se.typedef.vertx;

import io.vertx.core.Vertx;

public class Application {

  public static void main(String[] args) {
    final DependentServiceA dependentServiceA = new DependentServiceA();
    final ApiVerticle apiVerticle = new ApiVerticle(dependentServiceA);

    final Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(apiVerticle);
  }
}

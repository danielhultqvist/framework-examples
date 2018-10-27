package se.typedef.vertx;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import me.escoffier.vertx.completablefuture.VertxCompletableFuture;
import se.typedef.common.ResponseModel;

public class ApiVerticle extends AbstractVerticle {

  private static final int PORT = 8082;

  private DependentServiceA dependentServiceA;

  public ApiVerticle(final DependentServiceA dependentServiceA) {
    this.dependentServiceA = dependentServiceA;
  }

  @Override
  public void start() {
    final Router router = Router.router(vertx);

    router
        .get("/get")
        .handler(
            rc -> {
              VertxCompletableFuture.from(vertx, dependentServiceA.performBusinessLogic())
                  .thenAccept(
                      a -> rc.response().end(Json.encodePrettily(new ResponseModel(a.eventId))));
            });

    vertx
        .createHttpServer()
        .requestHandler(router::accept)
        .listen(
            PORT,
            (startResult) -> {
              if (startResult.succeeded()) {
                System.out.println("Application listening on port " + PORT);
              } else {
                System.out.println("Application failed to start: " + startResult.cause());
              }
            });
  }
}

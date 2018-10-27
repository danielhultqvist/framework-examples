package se.typedef.javalin;

import io.javalin.Javalin;
import se.typedef.common.DomainModel;
import se.typedef.common.RequestModel;
import se.typedef.common.ResponseModel;

import java.util.concurrent.CompletableFuture;

class Routes {

  private DependentServiceA dependentServiceA;

  Routes(final DependentServiceA dependentServiceA) {
    this.dependentServiceA = dependentServiceA;
  }

  void register(final Javalin app, final DependentServiceA dependentServiceA) {
    app.get(
        "/get",
        ctx -> {
          ctx.json(
              dependentServiceA
                  .performBusinessLogic()
                  .thenApply(domainModel -> new ResponseModel(domainModel.eventId)));
        });

    app.post(
        "/post",
        ctx -> {
            final RequestModel requestModel = ctx.bodyAsClass(RequestModel.class);

            ctx.json(
              dependentServiceA
                  .performBusinessLogic(requestModel)
                  .thenApply(domainModel -> new ResponseModel(domainModel.eventId)));
        });
  }
}

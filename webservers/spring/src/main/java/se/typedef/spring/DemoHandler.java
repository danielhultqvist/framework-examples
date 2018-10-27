package se.typedef.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import se.typedef.common.RequestModel;
import se.typedef.common.ResponseModel;

@Component
public class DemoHandler {

  @Autowired DependentServiceA dependentServiceA;

  public Mono<ServerResponse> get(ServerRequest request) {
    return dependentServiceA
        .performBusinessLogic()
        .flatMap(
            a ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(new ResponseModel(a.eventId))));
  }

  public Mono<ServerResponse> postWithData(ServerRequest request) {
    return request
        .bodyToMono(RequestModel.class)
        .flatMap(requestModel -> dependentServiceA.performBusinessLogic(requestModel))
        .flatMap(
            a ->
                ServerResponse.ok()
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.fromObject(new ResponseModel(a.eventId))));
  }
}

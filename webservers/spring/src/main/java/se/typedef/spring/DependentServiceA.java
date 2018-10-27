package se.typedef.spring;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import se.typedef.common.DomainModel;
import se.typedef.common.RequestModel;

import java.util.UUID;

@Service
class DependentServiceA {

  Mono<DomainModel> performBusinessLogic() {
    final DomainModel domainModel = DomainModel.create(UUID.randomUUID().toString());

    // Replace with real async operations
    return Mono.just(domainModel);
  }

  Mono<DomainModel> performBusinessLogic(final RequestModel requestModel) {
    final String upperCasedId = requestModel.id.toUpperCase();
    final DomainModel domainModel = DomainModel.create(upperCasedId);

    // Replace with real async operations
    return Mono.just(domainModel);
  }
}

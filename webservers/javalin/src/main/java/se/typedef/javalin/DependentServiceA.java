package se.typedef.javalin;

import se.typedef.common.DomainModel;
import se.typedef.common.RequestModel;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

class DependentServiceA {

    CompletableFuture<DomainModel> performBusinessLogic() {
        final DomainModel domainModel = DomainModel.create(UUID.randomUUID().toString());

        // Replace with real async operations
        return CompletableFuture.completedFuture(domainModel);
    }

    CompletableFuture<DomainModel> performBusinessLogic(final RequestModel requestModel) {
        final String upperCasedId = requestModel.id.toUpperCase();
        final DomainModel domainModel = DomainModel.create(upperCasedId);

        // Replace with real async operations
        return CompletableFuture.completedFuture(domainModel);
    }
}

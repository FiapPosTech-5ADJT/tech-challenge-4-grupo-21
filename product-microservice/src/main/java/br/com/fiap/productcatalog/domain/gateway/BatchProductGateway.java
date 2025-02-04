package br.com.fiap.productcatalog.domain.gateway;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;

public interface BatchProductGateway {

    public void execute(BatchProductRequest batchProductRequest);
}

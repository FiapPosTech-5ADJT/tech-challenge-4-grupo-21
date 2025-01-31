package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;
import br.com.fiap.productcatalog.domain.gateway.BatchProductGateway;

public class BatchProductService {

    private final BatchProductGateway batchProductGateway;

    public BatchProductService(BatchProductGateway batchProductGateway) {
        this.batchProductGateway = batchProductGateway;
    }

    public void execute(BatchProductRequest batchProductRequest) {
        batchProductGateway.execute(batchProductRequest);
    }
}

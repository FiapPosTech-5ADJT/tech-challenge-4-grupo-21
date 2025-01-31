package br.com.fiap.productcatalog.application.usecases;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;
import br.com.fiap.productcatalog.domain.service.BatchProductService;

public class BatchProductImportUseCase {

    private final BatchProductService batchProductService;

    public BatchProductImportUseCase(BatchProductService batchProductService) {
        this.batchProductService = batchProductService;
    }

    public void execute(BatchProductRequest batchProductRequest) {
        batchProductService.execute(batchProductRequest);
    }
}

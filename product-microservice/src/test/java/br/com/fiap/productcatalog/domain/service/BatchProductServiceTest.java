package br.com.fiap.productcatalog.domain.service;

import br.com.fiap.productcatalog.application.dto.BatchProductRequest;
import br.com.fiap.productcatalog.domain.gateway.BatchProductGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatchProductServiceTest {

    @Mock
    private BatchProductGateway batchProductGateway;

    @InjectMocks
    private BatchProductService batchProductService;

    private BatchProductRequest batchProductRequest;

    @BeforeEach
    void setup() {
        batchProductRequest = new BatchProductRequest(new byte[0], 10L);
    }

    @Test
    void execute_ShouldCallBatchProductGateway() {
        batchProductService.execute(batchProductRequest);
        verify(batchProductGateway, times(1)).execute(batchProductRequest);
    }
}

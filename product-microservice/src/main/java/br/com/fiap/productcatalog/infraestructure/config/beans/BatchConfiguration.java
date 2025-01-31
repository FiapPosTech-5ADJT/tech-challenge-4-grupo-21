package br.com.fiap.productcatalog.infraestructure.config.beans;

import br.com.fiap.productcatalog.application.usecases.BatchProductImportUseCase;
import br.com.fiap.productcatalog.domain.gateway.BatchProductGateway;
import br.com.fiap.productcatalog.domain.service.BatchProductService;
import br.com.fiap.productcatalog.infraestructure.gateway.BatchProductGatewayImpl;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfiguration {

    @Bean
    BatchProductImportUseCase batchProductImportUseCase(BatchProductService batchProductService) {
        return new BatchProductImportUseCase(batchProductService);
    }

    @Bean
    BatchProductService batchProductService(BatchProductGateway batchProductGateway) {
        return new BatchProductService(batchProductGateway);
    }

    @Bean
    BatchProductGateway batchProductGateway(JobLauncher jobLauncher,
                                            Job productBatchImportJob) {
        return new BatchProductGatewayImpl(jobLauncher, productBatchImportJob);
    }
}

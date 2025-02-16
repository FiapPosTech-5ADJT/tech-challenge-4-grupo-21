package br.com.fiap.logistic.config;

import br.com.fiap.logistic.adapter.DeliveryPersonConverter;
import br.com.fiap.logistic.gateway.DeliveryPersonGateway;
import br.com.fiap.logistic.gateway.impl.DeliveryPersonGatewayImpl;
import br.com.fiap.logistic.repository.DeliveryPersonRepository;
import br.com.fiap.logistic.service.DeliveryPersonService;
import br.com.fiap.logistic.service.OrderService;
import br.com.fiap.logistic.service.TrackingService;
import br.com.fiap.logistic.service.impl.DeliveryPersonServiceImpl;
import br.com.fiap.logistic.usecase.AssignDeliveryPersonUseCase;
import br.com.fiap.logistic.usecase.CompleteDeliveryUseCase;
import br.com.fiap.logistic.usecase.CreateDeliveryPersonUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeliveryPersonConfig {

    @Bean
    DeliveryPersonConverter deliveryPersonConverter() {
        return new DeliveryPersonConverter();
    }

    @Bean
    DeliveryPersonService deliveryPersonService(DeliveryPersonGateway deliveryPersonGateway,
                                                OrderService orderService,
                                                DeliveryPersonConverter deliveryPersonConverter,
                                                TrackingService trackingService) {
        return new DeliveryPersonServiceImpl(deliveryPersonGateway, orderService, deliveryPersonConverter, trackingService);
    }

    @Bean
    DeliveryPersonGateway deliveryPersonGateway(DeliveryPersonRepository deliveryPersonRepository) {
        return new DeliveryPersonGatewayImpl(deliveryPersonRepository);
    }

    @Bean
    CreateDeliveryPersonUseCase createDeliveryPersonUseCase(DeliveryPersonService deliveryPersonService) {
        return new CreateDeliveryPersonUseCase(deliveryPersonService);
    }


    @Bean
    AssignDeliveryPersonUseCase assignDeliveryPersonUseCase(DeliveryPersonService deliveryPersonService) {
        return new AssignDeliveryPersonUseCase(deliveryPersonService);
    }


    @Bean
    CompleteDeliveryUseCase completeDeliveryUseCase(DeliveryPersonService deliveryPersonService) {
        return new CompleteDeliveryUseCase(deliveryPersonService);
    }

}

package br.com.fiap.logistic.config;

import br.com.fiap.logistic.adapter.TrackingConverter;
import br.com.fiap.logistic.gateway.TrackingGateway;
import br.com.fiap.logistic.gateway.impl.TrackingGatewayImpl;
import br.com.fiap.logistic.repository.TrackingRepository;
import br.com.fiap.logistic.service.TrackingService;
import br.com.fiap.logistic.service.impl.TrackingServiceImpl;
import br.com.fiap.logistic.usecase.GetTrackingByLatitudeAndLongitudeUseCase;
import br.com.fiap.logistic.usecase.UpdateTrackingLatitudeAndLongitudeUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TrackingConfig {

    @Bean
    TrackingService trackingService(TrackingGateway trackingGateway, TrackingConverter trackingConverter) {
        return new TrackingServiceImpl(trackingGateway, trackingConverter);
    }

    @Bean
    TrackingGateway trackingGateway(TrackingRepository trackingRepository) {
        return new TrackingGatewayImpl(trackingRepository);
    }

    @Bean
    UpdateTrackingLatitudeAndLongitudeUseCase updateTrackingLatitudeAndLongitudeUseCase(TrackingService trackingService) {
        return new UpdateTrackingLatitudeAndLongitudeUseCase(trackingService);
    }

    @Bean
    GetTrackingByLatitudeAndLongitudeUseCase getTrackingByLatitudeAndLongitudeUseCase(TrackingService trackingService) {
        return new GetTrackingByLatitudeAndLongitudeUseCase(trackingService);
    }

    @Bean
    TrackingConverter trackingConverter() {
        return new TrackingConverter();
    }
}

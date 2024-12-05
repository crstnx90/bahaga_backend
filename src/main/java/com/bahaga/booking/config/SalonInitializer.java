package com.bahaga.booking.config;

import com.bahaga.booking.model.Salon;
import com.bahaga.booking.repository.SalonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SalonInitializer {

    @Bean
    CommandLineRunner initSalones(SalonRepository salonRepository) {
        return args -> {
            if (salonRepository.count() == 0) {
                salonRepository.save(new Salon(null, "Salón Oro", "Salón con decoración dorada y servicios básicos", 150, 500.00));
                salonRepository.save(new Salon(null, "Salón Diamante", "Salón con decoración premium y servicios avanzados", 250, 1000.00));
                salonRepository.save(new Salon(null, "Salón Platinum", "Salón de lujo con capacidad máxima y servicios VIP", 500, 2000.00));
            }
        };
    }
}

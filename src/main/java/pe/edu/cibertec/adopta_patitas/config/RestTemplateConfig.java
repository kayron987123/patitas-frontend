package pe.edu.cibertec.adopta_patitas.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplateAutenticacion(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8090/autenticacion")
                .setConnectTimeout(Duration.ofSeconds(5)) // tiempo de espera maximo para establecer la conexion
                .setReadTimeout(Duration.ofSeconds(10)) // tiempo de espera maximo para recibir la respuesta total
                .build();
    }

    @Bean
    public RestTemplate restTemplateFinanzas(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8090/finanzas")
                .setReadTimeout(Duration.ofSeconds(30))
                .build();
    }

    @Bean
    public RestTemplate restTemplateReporteria(RestTemplateBuilder builder) {
        return builder
                .rootUri("http://localhost:8090/reporteria")
                .setReadTimeout(Duration.ofSeconds(60))
                .build();
    }
}

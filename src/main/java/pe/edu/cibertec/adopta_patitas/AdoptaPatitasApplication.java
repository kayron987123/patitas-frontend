package pe.edu.cibertec.adopta_patitas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdoptaPatitasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdoptaPatitasApplication.class, args);
	}

}

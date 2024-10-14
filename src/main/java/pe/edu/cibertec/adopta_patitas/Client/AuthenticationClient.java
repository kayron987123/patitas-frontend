package pe.edu.cibertec.adopta_patitas.Client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pe.edu.cibertec.adopta_patitas.dto.LoginRequestDTO;
import pe.edu.cibertec.adopta_patitas.dto.LoginResponseDTO;

@FeignClient(name = "autenticacion", url = "http://localhost:8090/autenticacion")
public interface AuthenticationClient {

    @PostMapping("/login")
    ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO);

}

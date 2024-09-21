package pe.edu.cibertec.adopta_patitas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pe.edu.cibertec.adopta_patitas.dto.LoginRequestDTO;
import pe.edu.cibertec.adopta_patitas.dto.LoginResponseDTO;
import pe.edu.cibertec.adopta_patitas.viewmodel.LoginModel;

@Controller
@RequestMapping("/login")
public class LoginController {

    private final RestTemplate restTemplate= new RestTemplate();
    private final String BACKEND_URL = "http://localhost:8090/autenticacion/login";

    @GetMapping("/inicio")
    public String inicio(Model model) {

        LoginModel loginModel = new LoginModel("00", "", "");
        model.addAttribute("loginModel", loginModel);
        return "inicio";
    }

    @PostMapping("/autenticar")
    public String autenticar(@RequestParam("tipoDocumento") String tipoDocumento,
                             @RequestParam("numeroDocumento") String numeroDocumento,
                             @RequestParam("password") String password,
                             Model model){

        //validar campos de entrada
        if(tipoDocumento == null || tipoDocumento.trim().length() == 0 ||
        numeroDocumento == null || numeroDocumento.trim().length() == 0 ||
        password == null || password.trim().length() == 0){
            LoginModel loginModel = new LoginModel("01", "Error: Debe completar correctamente sus credenciales", "");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }

        //invocar servicio de autenticacion
        LoginRequestDTO loginRequestDTO  = new LoginRequestDTO(tipoDocumento, numeroDocumento, password);

        try {
            LoginResponseDTO loginResponseDTO = restTemplate.postForObject(BACKEND_URL, loginRequestDTO, LoginResponseDTO.class);

            if(loginResponseDTO != null && "00".equals(loginResponseDTO.codigo())){
                LoginModel loginModel = new LoginModel("00", "", loginResponseDTO.nombre());
                model.addAttribute("loginModel", loginModel);
                return "principal";
            }else {
                LoginModel loginModel = new LoginModel("01", loginResponseDTO != null ? loginResponseDTO.mensaje() : "Error en la autenticación", "");
                model.addAttribute("loginModel", loginModel);
                return "inicio";
            }
        } catch (Exception e) {
            LoginModel loginModel = new LoginModel("99", "Error al comunicar al servidor", "");
            model.addAttribute("loginModel", loginModel);
            return "inicio";
        }

    }

}

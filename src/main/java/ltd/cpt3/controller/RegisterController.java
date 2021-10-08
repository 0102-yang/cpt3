package ltd.cpt3.controller;

import lombok.Data;
import ltd.cpt3.entity.dto.RegisterUserDto;
import ltd.cpt3.service.register.RegisterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author yang
 */
@RestController
@RequestMapping("/register")
@CrossOrigin("*")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping("")
    public ResponseEntity<String> register(@Valid RegisterUser registerUser) {
        RegisterUserDto registerUserDto = new RegisterUserDto().setUsername(registerUser.getUsername()).setPassword(registerUser.getPassword()).setAge(registerUser.getAge()).setGender(registerUser.getGender() != 0);
        this.registerService.register(registerUserDto);
        return ResponseEntity.ok("Register success");
    }

}

@Data
class RegisterUser {

    private String username;

    private String password;

    private Integer age;

    private Integer gender;

}

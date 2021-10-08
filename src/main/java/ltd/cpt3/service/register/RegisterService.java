package ltd.cpt3.service.register;

import lombok.extern.slf4j.Slf4j;
import ltd.cpt3.entity.data.UserDo;
import ltd.cpt3.entity.dto.RegisterUserDto;
import ltd.cpt3.repository.UserDoRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author yang
 */
@Service
@Slf4j
public class RegisterService {

    private final UserDoRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public RegisterService(UserDoRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterUserDto registerUser) throws RuntimeException {
        String username = registerUser.getUsername();
        if (this.userRepository.existsByUsername(username)) {
            throw new RuntimeException("Username is already exists");
        }

        UserDo user = new UserDo();
        String encryptedPassword = this.passwordEncoder.encode(registerUser.getPassword());
        user.setUsername(username).setPassword(encryptedPassword).setAge(registerUser.getAge()).setGender(registerUser.getGender());
        // 暂时为USER权限
        this.userRepository.save(user);
        RegisterService.log.info("User {} is successfully register.", username);
    }

}

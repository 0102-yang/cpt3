package ltd.cpt3.service.business;

import ltd.cpt3.entity.data.UserDo;
import ltd.cpt3.entity.dto.UserDto;
import ltd.cpt3.repository.UserDoRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author yang
 */
@Service
public class UserInfoService {

    private final UserDoRepository userDoRepository;

    public UserInfoService(UserDoRepository userDoRepository) {
        this.userDoRepository = userDoRepository;
    }

    public UserDto getUserByUsername(String username) throws UsernameNotFoundException {
        UserDo userDo = this.userDoRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new UserDto().setUsername(userDo.getUsername()).setPassword(userDo.getPassword()).setAge(userDo.getAge()).setGender(userDo.getGender());
    }

}

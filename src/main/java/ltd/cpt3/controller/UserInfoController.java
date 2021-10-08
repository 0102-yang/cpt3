package ltd.cpt3.controller;

import lombok.Data;
import lombok.experimental.Accessors;
import ltd.cpt3.entity.dto.UserDto;
import ltd.cpt3.service.business.UserInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;

/**
 * @author yang
 */
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserInfoController {

    private final UserInfoService userInfoService;

    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserVo> getUser(@PathVariable @NotEmpty String username) {
        UserDto userDto = this.userInfoService.getUserByUsername(username);
        UserVo userVo = new UserVo().setUsername(userDto.getUsername()).setAge(userDto.getAge()).setGender(userDto.getGender() ? "男" : "女");
        return ResponseEntity.ok(userVo);
    }

}

@Data
@Accessors(chain = true)
class UserVo {

    private String username;

    private Integer age;

    private String gender;

}

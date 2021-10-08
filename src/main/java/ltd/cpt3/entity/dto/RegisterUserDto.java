package ltd.cpt3.entity.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yang
 */
@Data
@Accessors(chain = true)
public class RegisterUserDto {

    private String username;

    private String password;

    private Integer age;

    private Boolean gender;

}

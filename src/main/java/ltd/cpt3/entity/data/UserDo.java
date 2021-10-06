package ltd.cpt3.entity.data;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author yang
 */
@Data
@Entity
@Table(name = "user")
@Accessors(chain = true)
public class UserDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "age")
    private Integer age = null;

    /**
     * male for 1 and female for 0
     */
    @Column(name = "gender")
    private Boolean gender;

}

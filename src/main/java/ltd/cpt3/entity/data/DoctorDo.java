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
@Table(name = "doctor")
@Accessors(chain = true)
public class DoctorDo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name = "NULL";

    @Column(name = "title")
    private String title = "NULL";

    @Column(name = "hospital_name")
    private String hospitalName = "NULL";

    @Column(name = "department_name")
    private String departmentName = "NULL";

    @Column(name = "clinic_name")
    private String clinicName = "NULL";

    @Column(name = "score")
    private Float score = null;

    @Column(name = "hits")
    private Integer hits = null;

    @Column(name = "labels")
    private String labels = "NULL";

    @Column(name = "goodat")
    private String goodat = "NULL";

    @Column(name = "about")
    private String about = "NULL";

}

package ltd.cpt3.repository;

import ltd.cpt3.entity.data.DoctorDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Table;

/**
 * @author yang
 */
@Repository
@Table(name = "doctor")
public interface DoctorDoRepository extends JpaRepository<DoctorDo, Integer> {

}

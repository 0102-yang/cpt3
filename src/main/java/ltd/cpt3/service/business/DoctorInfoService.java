package ltd.cpt3.service.business;

import ltd.cpt3.entity.data.DoctorDo;
import ltd.cpt3.repository.DoctorDoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

/**
 * @author yang
 */
@Service
public class DoctorInfoService {

    private final DoctorDoRepository doctorDoRepository;

    public DoctorInfoService(DoctorDoRepository doctorDoRepository) {
        this.doctorDoRepository = doctorDoRepository;
    }

    public List<DoctorDo> getDoctors() {
        List<DoctorDo> doctors = this.doctorDoRepository.findAll();
        int amount = 9;
        if (doctors.size() <= amount) {
            return doctors;
        } else {
            TreeSet<Integer> numSet = new TreeSet<>();
            List<DoctorDo> res = new ArrayList<>();
            int i = 0;
            while (true) {
                Random random = new Random();
                int r = random.nextInt(doctors.size());

                if (numSet.contains(r)) {
                    continue;
                }
                res.add(doctors.get(r));
                numSet.add(r);

                i++;
                if (i >= amount) {
                    break;
                }
            }
            return res;
        }
    }

}

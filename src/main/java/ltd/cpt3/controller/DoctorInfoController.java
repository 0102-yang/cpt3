package ltd.cpt3.controller;

import ltd.cpt3.entity.data.DoctorDo;
import ltd.cpt3.service.business.DoctorInfoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author yang
 */
@RestController
@CrossOrigin("*")
public class DoctorInfoController {

    private final DoctorInfoService doctorInfoService;

    public DoctorInfoController(DoctorInfoService doctorInfoService) {
        this.doctorInfoService = doctorInfoService;
    }

    @GetMapping("/doctors")
    public ResponseEntity<List<DoctorDo>> getDoctors() {
        List<DoctorDo> res = this.doctorInfoService.getDoctors();
        return ResponseEntity.ok(res);
    }

}

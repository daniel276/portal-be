package com.studentportal.portal.web;

import com.studentportal.portal.domain.Module;
import com.studentportal.portal.domain.ModuleEnrollment;
import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.repositories.ModuleEnrollmentRepository;
import com.studentportal.portal.repositories.ModuleRepository;
import com.studentportal.portal.repositories.UserLoginRepository;
import com.studentportal.portal.services.MapValidationErrorService;
import com.studentportal.portal.services.ModuleEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/enroll")
@CrossOrigin
public class ModuleEnrollmentController {

    @Autowired
    ModuleEnrollmentRepository enrollmentRepository;

    @Autowired
    ModuleEnrollmentService enrollmentService;

    @Autowired
    MapValidationErrorService errorService;

    @GetMapping("/student")
    public Iterable<?> findAllByStudentId(@RequestParam Long id){
        return enrollmentRepository.findAllByStudent_Id(id);
    }

    @PostMapping("/add/{module_code}/{student_id}")
    public ResponseEntity<?> saveEnrollment(@Valid @RequestBody ModuleEnrollment moduleEnrollment, BindingResult result, @PathVariable String module_code, @PathVariable Long student_id){

        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);

        if(errorMap != null) {return errorMap;}

        ModuleEnrollment moduleEnrollment1 = enrollmentService.saveEnrollment(moduleEnrollment, module_code, student_id);

        return new ResponseEntity<>(moduleEnrollment1, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateEnrollment(@Valid @RequestBody ModuleEnrollment moduleEnrollment, BindingResult result){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);

        if(errorMap != null) {return errorMap;}

        ModuleEnrollment moduleEnrollment1 = enrollmentService.updateEnrollment(moduleEnrollment);

        return new ResponseEntity<>(moduleEnrollment1, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteEnrollment(@PathVariable Long id){

        enrollmentService.deleteEnrollment(id);

        return new ResponseEntity<>("Entry Successfully deleted", HttpStatus.OK);
    }

    @GetMapping("/module")
    public Iterable<?> findEnrolledStudentByModuleCode(@RequestParam String code){
//        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
//
//        if(errorMap != null) {return errorMap;}

        return enrollmentService.findAllByModuleCode(code);
    }

}

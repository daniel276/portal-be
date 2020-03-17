package com.studentportal.portal.services;

import com.studentportal.portal.domain.Module;
import com.studentportal.portal.domain.ModuleEnrollment;
import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.exceptions.UsernameAlreadyExistsException;
import com.studentportal.portal.repositories.ModuleEnrollmentRepository;
import com.studentportal.portal.repositories.ModuleRepository;
import com.studentportal.portal.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleEnrollmentService {

    @Autowired
    ModuleEnrollmentRepository enrollmentRepository;

    @Autowired
    UserLoginRepository userLoginRepository;

    @Autowired
    ModuleRepository moduleRepository;

    public Iterable<?> getAll(){
        return enrollmentRepository.findAll();
    }

    public Iterable<ModuleEnrollment> findAllByStudentId(Long id){
        return enrollmentRepository.findAllByStudent_Id(id);
    }

    public ModuleEnrollment saveEnrollment(ModuleEnrollment moduleEnrollment, String module_code, Long student_id){

        Module module = moduleRepository.findModuleByCode(module_code);

        UserLogin user = userLoginRepository.findUserLoginById(student_id);

        moduleEnrollment.setModule(module);
        moduleEnrollment.setStudent(user);

        return enrollmentRepository.save(moduleEnrollment);

    }

    public ModuleEnrollment updateEnrollment(ModuleEnrollment moduleEnrollment){

        return enrollmentRepository.save(moduleEnrollment);
    }

    public void deleteEnrollment(Long id){
        ModuleEnrollment moduleEnrollment = enrollmentRepository.findModuleEnrollmentById(id);

        enrollmentRepository.delete(moduleEnrollment);
    }

    public Iterable<ModuleEnrollment> findAllByModuleCode(String code){
        return enrollmentRepository.findModuleEnrollmentsByModuleCode(code);
    }

}

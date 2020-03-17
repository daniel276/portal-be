package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.ModuleEnrollment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleEnrollmentRepository extends CrudRepository<ModuleEnrollment, Long> {

    @Override
    Iterable<ModuleEnrollment> findAll();

    Iterable<ModuleEnrollment> findAllByStudent_Id(Long id);

    Iterable<ModuleEnrollment> findModuleEnrollmentsByModuleCode(String code);

    ModuleEnrollment findModuleEnrollmentById(Long id);

}

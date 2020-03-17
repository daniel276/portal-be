package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {

    @Override
    Iterable<Module> findAll();
    
    Module findModuleByCode(String code);

    Module findModuleById(Long id);


}

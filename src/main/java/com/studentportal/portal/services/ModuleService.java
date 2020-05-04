package com.studentportal.portal.services;

import com.studentportal.portal.domain.Module;
import com.studentportal.portal.exceptions.UsernameAlreadyExistsException;
import com.studentportal.portal.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleService {

    @Autowired
    ModuleRepository moduleRepository;

    public Module saveOrUpdate(Module module){
            return moduleRepository.save(module);
    }

    public Module findModuleByCode(String code){
        return moduleRepository.findModuleByCode(code);
    }

    public Module findModuleById(Long id){
        return moduleRepository.findModuleById(id);

    }

    public void deleteModule(Long id){
        Module module = moduleRepository.findModuleById(id);

        moduleRepository.delete(module);
    }

    public Iterable<Module> findAllModule() { return moduleRepository.findAll(); }

}

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
        try{
            return moduleRepository.save(module);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("ahaha"); // TODO MAKE CUSTOM EXCEPTION FOR THIS
        }
    }

    public Module findModuleByCode(String code){
        return moduleRepository.findModuleByCode(code);
    }

    public Module findModuleById(Long id){
        Module module = moduleRepository.findModuleById(id);
        if(module == null){
            throw new UsernameAlreadyExistsException("OL");
        }

        return module;
    }

    public void deleteModule(Long id){
        Module module = moduleRepository.findModuleById(id);

        moduleRepository.delete(module);
    }

    public Iterable<Module> findAllModule() { return moduleRepository.findAll(); }

}

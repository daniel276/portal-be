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

    public Module save(Module module){
        try{
            return moduleRepository.save(module);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("ahaha");
        }
    }

    public Module findModuleByCode(String code){

        Module module = moduleRepository.findModuleByCode(code);

        if(module == null){
            throw new UsernameAlreadyExistsException("ok"); // TODO make new exception for this
        }

        return module;
    }

    public Module findModuleById(Long id){
        Module module = moduleRepository.findModuleById(id);
        if(module == null){
            throw new UsernameAlreadyExistsException("OL");
        }

        return module;
    }

    public Module updateModule(Module module){
        return moduleRepository.save(module);
    }

    public void deleteModule(Long id){
        Module module = moduleRepository.findModuleById(id);

        moduleRepository.delete(module);
    }

    public Iterable<Module> findAllModule() { return moduleRepository.findAll(); }

}

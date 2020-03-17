package com.studentportal.portal.services;

import com.studentportal.portal.domain.Class;
import com.studentportal.portal.domain.Module;
import com.studentportal.portal.exceptions.UsernameAlreadyExistsException;
import com.studentportal.portal.repositories.ClassRepository;
import com.studentportal.portal.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    ModuleRepository moduleRepository;

    public Iterable<Class> findAll(){
        return classRepository.findAll();
    }

    public Class saveClass(Class classModule, String moduleCode){

        Module module = moduleRepository.findModuleByCode(moduleCode);

        classModule.setModule(module);

        try{
            return classRepository.save(classModule);
        }catch (Exception e){
            throw new UsernameAlreadyExistsException("ok"); //TODO make new exception for this
        }
    }

    public Class updateClass(Class classModule){
        return classRepository.save(classModule);
    }

    public void deleteClass(Long id){
        Class classModule = classRepository.findClassById(id);

        classRepository.delete(classModule);
    }

    public Iterable<Class> findClassesByModuleCode(String code){
        return classRepository.findAllByModuleCode(code);
    }

}

package com.studentportal.portal.services;

import com.studentportal.portal.domain.Class;
import com.studentportal.portal.domain.Module;
import com.studentportal.portal.exceptions.UsernameAlreadyExistsException;
import com.studentportal.portal.repositories.ClassRepository;
import com.studentportal.portal.repositories.ModuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ClassService {

    @Autowired
    ClassRepository classRepository;

    @Autowired
    ModuleRepository moduleRepository;

    public Iterable<Class> findAll(){
        return classRepository.findAll();
    }

    public Class findClass(Long id){
        return classRepository.findClassById(id);
    }

    public Class saveClass(Class classModule, Long module_id){

        Module module = moduleRepository.findModuleById(module_id);

        classModule.setModule(module);
        return classRepository.save(classModule);

    }

    public Class updateClass(Class classModule){
        return classRepository.save(classModule);
    }

    public void deleteClass(Long id){
        Class classModule = classRepository.findClassById(id);

        classRepository.delete(classModule);
    }

    public Iterable<Class> findAllByModuleId(Long id) { return classRepository.findAllByModuleId(id);}

}

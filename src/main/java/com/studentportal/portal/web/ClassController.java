package com.studentportal.portal.web;

import com.studentportal.portal.domain.Class;
import com.studentportal.portal.services.ClassService;
import com.studentportal.portal.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/classes")
@CrossOrigin
public class ClassController {

    @Autowired
    ClassService classService;

    @Autowired
    MapValidationErrorService errorService;

    @GetMapping("/all")
    public Iterable<?> findAllClasses(){
        return classService.findAll();
    }

    @GetMapping("/find/{class_id}")
    public ResponseEntity<?> getClassById(@PathVariable Long class_id){
        Class class1 = classService.findClass(class_id);
        return new ResponseEntity<>(class1, HttpStatus.OK);
    }

    @PostMapping("/{module_id}")
    public ResponseEntity<?> saveClass(@Valid @RequestBody Class classModule, BindingResult result, @PathVariable Long module_id){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
        if(errorMap != null){
            return errorMap;
        }

        Class classModule1 = classService.saveClass(classModule, module_id);

        return new ResponseEntity<>(classModule1, HttpStatus.CREATED);

    }

    @GetMapping("/{module_id}")
    public Iterable<?> findClassesByModuleId(@PathVariable Long module_id){
        return classService.findAllByModuleId(module_id);
    }


}

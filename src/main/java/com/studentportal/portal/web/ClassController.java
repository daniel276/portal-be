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

    @PostMapping("/{module_code}")
    public ResponseEntity<?> saveClass(@Valid @RequestBody Class classModule, BindingResult result, @PathVariable String module_code){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
        if(errorMap != null){
            return errorMap;
        }

        Class classModule1 = classService.saveClass(classModule, module_code);

        return new ResponseEntity<>(classModule1, HttpStatus.OK);

    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateClass(@Valid @RequestBody Class classModule, BindingResult result){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
        if(errorMap != null){
            return errorMap;
        }

        Class classModule1 = classService.updateClass(classModule);

        return new ResponseEntity<>(classModule1, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteClass(@PathVariable Long id){
        classService.deleteClass(id);

        return new ResponseEntity<>("Entry Successfully Deleted", HttpStatus.OK);
    }

    @GetMapping("/{module_code}")
    public Iterable<?> findClassesByModule(@PathVariable String module_code){
        return classService.findClassesByModuleCode(module_code);
    }


}

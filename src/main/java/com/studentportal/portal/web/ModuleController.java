package com.studentportal.portal.web;

import com.studentportal.portal.domain.Module;
import com.studentportal.portal.services.MapValidationErrorService;
import com.studentportal.portal.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/module")
@CrossOrigin
public class ModuleController {

    @Autowired
    MapValidationErrorService errorService;

    @Autowired
    ModuleService moduleService;

    @PostMapping("/add")
    public ResponseEntity<?> saveModule(@Valid @RequestBody Module module, BindingResult result){

        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
        if(errorMap != null){
            return errorMap;
        }

        Module module1 = moduleService.saveOrUpdate(module);

        return new ResponseEntity<>(module1, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<?> getModuleByCode(@RequestParam String code){
        Module module = moduleService.findModuleByCode(code);

        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @GetMapping("/{module_id}")
    public ResponseEntity<?> getModuleById(@PathVariable Long module_id){
        Module module = moduleService.findModuleById(module_id);
        return new ResponseEntity<>(module, HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateModule(Module module){
        Module module1 = moduleService.saveOrUpdate(module);

        return new ResponseEntity<>(module1, HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<Module> getAllModule(){
        return moduleService.findAllModule();
    }

}

package com.studentportal.portal.web;


import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.payload.LoginRequest;
import com.studentportal.portal.services.LoginService;
import com.studentportal.portal.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    MapValidationErrorService errorService;

    @Autowired
    LoginService loginService;

//    @PostMapping("/login")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
//        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
//
//        if(errorMap != null){
//            return errorMap;
//        }
//
//        return ResponseEntity.ok();
//    }

//    @PostMapping("register")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody UserLogin user, BindingResult result){
//
//    }



}

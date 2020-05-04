package com.studentportal.portal.web;


import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.payload.JWTLoginSuccessResponse;
import com.studentportal.portal.payload.LoginRequest;
import com.studentportal.portal.security.JWTTokenProvider;
import com.studentportal.portal.services.LoginService;
import com.studentportal.portal.services.MapValidationErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.studentportal.portal.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class LoginController {

    @Autowired
    MapValidationErrorService errorService;

    @Autowired
    LoginService loginService;

    @Autowired
    JWTTokenProvider tokenProvider;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);

        if(errorMap != null){
            return errorMap;
        }
        //pass the request body to authManager class
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = TOKEN_PREFIX + tokenProvider.generateToken(auth);

        return ResponseEntity.ok(new JWTLoginSuccessResponse(true, jwt));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserLogin user, BindingResult result){

        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);

        if(errorMap != null){
            return errorMap;
        }
        user.setRole(user.getRole().toUpperCase());
        UserLogin newUser = loginService.saveUserLogin(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateUser(@RequestBody UserLogin userLogin, BindingResult result){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
        if(errorMap != null){
            return errorMap;
        }

        UserLogin newUser = loginService.saveUserLogin(userLogin);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUser(@PathVariable Long user_id){
        UserLogin user = loginService.findUserById(user_id);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/reset-password/{user_id}")
    public ResponseEntity<?> resetPassword(@PathVariable Long user_id){
        loginService.resetPassword(user_id);
        return new ResponseEntity<>("Password reset success", HttpStatus.OK);
    }

    @GetMapping("/all")
    public Iterable<UserLogin> getAll(){
        return loginService.findAll();
    }

}

package com.studentportal.portal.web;


import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.payload.JWTLoginSuccessResponse;
import com.studentportal.portal.payload.LoginRequest;
import com.studentportal.portal.security.JWTTokenProvider;
import com.studentportal.portal.services.LoginService;
import com.studentportal.portal.services.MapValidationErrorService;
import com.studentportal.portal.validator.UserValidator;
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
    UserValidator userValidator;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result){
        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);

        if(errorMap != null){
            return errorMap;
        }

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
        userValidator.validate(user, result);

        user.setConfirmPassword(""); // conceal confirmPassword in json response body

        ResponseEntity<?> errorMap = errorService.mapValidationErrorService(result);
        if(errorMap != null){
            return errorMap;
        }
        UserLogin newUser = loginService.saveUserLogin(user);

        return new ResponseEntity<>(newUser, HttpStatus.CREATED);

    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> getUserLogin(@PathVariable Long user_id){
        UserLogin user = loginService.findUserById(user_id);

        return new ResponseEntity<>(user, HttpStatus.OK);

    }

}

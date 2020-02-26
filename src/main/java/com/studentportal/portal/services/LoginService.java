package com.studentportal.portal.services;

import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.exceptions.UsernameAlreadyExistsException;
import com.studentportal.portal.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserLogin saveUserLogin(UserLogin newUserLogin){
        try{
            // unique username
            // password and confirmPassword is match
            // don't persist the confirm password
            newUserLogin.setPassword(bCryptPasswordEncoder.encode(newUserLogin.getPassword()));
            newUserLogin.setUsername(newUserLogin.getUsername());
            return userLoginRepository.save(newUserLogin);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username: "+ newUserLogin.getUsername()+ " already registered");
        }
    }

}

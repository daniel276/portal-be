package com.studentportal.portal.services;

import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.exceptions.UsernameAlreadyExistsException;
import com.studentportal.portal.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

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
            if(newUserLogin.getPassword() != null){
                System.out.println(newUserLogin.getPassword());
                newUserLogin.setPassword(bCryptPasswordEncoder.encode(newUserLogin.getPassword()));
            }

            if(newUserLogin.getPassword() == null){
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(newUserLogin.getDob());
                String initialPassword = newUserLogin.getLastName().concat(strDate);
                newUserLogin.setPassword(bCryptPasswordEncoder.encode(initialPassword));
            }

            Long id = ((Long) newUserLogin.getId());
            String strId = id.toString();

            if(newUserLogin.getUsername() == null){
                newUserLogin.setUsername(newUserLogin.getLastName().concat(strId));
            }
            System.out.println("id "+ strId);

            return userLoginRepository.save(newUserLogin);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username: "+ newUserLogin.getUsername()+ " already registered");
        }
    }

    public UserLogin findUserById(Long id){
        UserLogin userLogin = userLoginRepository.findUserLoginById(id);

        if(userLogin == null){
            throw new UsernameAlreadyExistsException("not found"); //TODO make new exception for userlogin
        }

        return userLogin;
    }

}

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
            if(newUserLogin.getPassword() != null){ // if password is provided then just continue and
                newUserLogin.setPassword(bCryptPasswordEncoder.encode(newUserLogin.getPassword()));
            }

            if(newUserLogin.getPassword() == null){ // if account newly created, generate initial password
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String strDate = dateFormat.format(newUserLogin.getDob());
                String initialPassword = newUserLogin.getLastName().toLowerCase().concat(strDate);
                newUserLogin.setPassword(bCryptPasswordEncoder.encode(initialPassword));
            }

            return userLoginRepository.save(newUserLogin);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username: "+ newUserLogin.getUsername()+ " already registered");
        }
    }

    public UserLogin findUserById(Long id){
        return userLoginRepository.findUserLoginById(id);
    }

    public UserLogin resetPassword(Long user_id){
        UserLogin user = userLoginRepository.findUserLoginById(user_id);
        //format password into this pattern
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(user.getDob());
        String initialPassword = user.getLastName().concat(strDate);
        user.setPassword(bCryptPasswordEncoder.encode(initialPassword));
        System.out.println(initialPassword);
        return userLoginRepository.save(user);
    }

    public Iterable<UserLogin> findAll(){
        return userLoginRepository.findAll();
    }

}

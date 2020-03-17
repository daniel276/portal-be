package com.studentportal.portal.services;

import com.studentportal.portal.domain.UserLogin;
import com.studentportal.portal.repositories.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        UserLogin userLogin = userLoginRepository.findByUsername(s);

        if(userLogin == null){
            throw new UsernameNotFoundException("User is not found");
        }

        return userLogin;

    }

    @Transactional
    public UserLogin loadUserById(Long id){
        UserLogin userLogin = userLoginRepository.findUserLoginById(id);

        if(userLogin == null){
            throw new UsernameNotFoundException("User is not found");
        }

        return userLogin;
    }
}

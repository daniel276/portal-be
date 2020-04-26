package com.studentportal.portal.validator;

import com.studentportal.portal.domain.UserLogin;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return UserLogin.class.equals(aClass);
    }

    @Override
    public void validate(Object object, Errors errors) {

        UserLogin user = (UserLogin) object;

//        if(user.getPassword().length() < 6){
//            errors.rejectValue("password", "Length", "Password must consist of minimum six characters");
//        }

//        if(!user.getPassword().equals(user.getConfirmPassword())){
//            errors.rejectValue("confirmPassword", "Match", "password must the same");
//        }
    }
}

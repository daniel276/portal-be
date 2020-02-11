package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLoginRepository extends CrudRepository<UserLogin, Long> {

    UserLogin findByUsername(String username);
    UserLogin getById(Long id);

}

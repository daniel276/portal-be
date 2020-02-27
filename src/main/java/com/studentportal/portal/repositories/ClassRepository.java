package com.studentportal.portal.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {

    @Override
    Iterable<Class> findAll();

//    Iterable<Class> findClassesBy


}

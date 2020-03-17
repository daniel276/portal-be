package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.Class;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {

    @Override
    Iterable<Class> findAll();

    Iterable<Class> findAllByModuleId(Long id);

    Iterable<Class> findAllByModuleCode(String code);

    Class findClassById(Long id);

//    Iterable<Class> findClassesBy


}

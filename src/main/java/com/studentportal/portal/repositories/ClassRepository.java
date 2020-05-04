package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.Class;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ClassRepository extends CrudRepository<Class, Long> {

    @Override
    Iterable<Class> findAll();

    Iterable<Class> findAllByModuleId(Long id);

    Class findClassById(Long id);

}

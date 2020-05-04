package com.studentportal.portal.repositories;

import com.studentportal.portal.domain.FaceBiometric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FaceBiometricsRepository extends CrudRepository<FaceBiometric, Long> {

    Iterable<FaceBiometric> findAllByUserLoginId(Long id);

    Iterable<FaceBiometric> findAllByUserLoginUsername(String username);

    FaceBiometric findFaceBiometricById(Long id);

}

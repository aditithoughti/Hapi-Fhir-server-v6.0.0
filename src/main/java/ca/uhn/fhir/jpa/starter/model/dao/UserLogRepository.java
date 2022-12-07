package ca.uhn.fhir.jpa.starter.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ca.uhn.fhir.jpa.starter.model.UserEntity;

@Repository
public interface UserLogRepository extends JpaRepository<UserEntity, Long> {


}

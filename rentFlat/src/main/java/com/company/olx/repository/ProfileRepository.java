package com.company.olx.repository;


import com.company.olx.entity.ProfileEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.NamedQuery;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity,Integer> {
    Optional<ProfileEntity> findByEmailAndPassword(String email, String password);
}

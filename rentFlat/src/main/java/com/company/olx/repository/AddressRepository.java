package com.company.olx.repository;

import com.company.olx.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity,Integer> {
}

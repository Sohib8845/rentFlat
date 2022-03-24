package com.company.olx.repository;

import com.company.olx.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity,Integer> {
}

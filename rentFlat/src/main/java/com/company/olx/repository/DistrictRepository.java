package com.company.olx.repository;


import com.company.olx.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DistrictRepository extends JpaRepository<DistrictEntity,Integer> {
    List<DistrictEntity> findDistrictEntityByCityId(Integer id);
}

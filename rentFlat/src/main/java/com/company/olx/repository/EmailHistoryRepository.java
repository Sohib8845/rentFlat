package com.company.olx.repository;

import com.company.olx.entity.EmailHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailHistoryRepository extends JpaRepository<EmailHistoryEntity,Integer> {
}

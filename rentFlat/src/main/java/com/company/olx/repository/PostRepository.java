package com.company.olx.repository;

import com.company.olx.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Integer> {
}

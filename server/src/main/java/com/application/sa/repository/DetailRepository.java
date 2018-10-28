package com.application.sa.repository;

import com.application.sa.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface DetailRepository extends JpaRepository<Detail, Long>{
    Detail findByDetailIds(Long detailIds);
}

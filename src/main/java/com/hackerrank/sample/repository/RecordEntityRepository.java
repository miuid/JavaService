package com.hackerrank.sample.repository;

import com.hackerrank.sample.entity.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordEntityRepository extends JpaRepository<RecordEntity, Long> {
}

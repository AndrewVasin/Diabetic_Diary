package com.diary.repository;

import com.diary.model.SugarLevelRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SugarLevelRepository extends JpaRepository<SugarLevelRecord, Long> {
}

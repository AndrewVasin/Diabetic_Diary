package com.diary.service;

import com.diary.model.SugarLevelRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public interface SugarLevelService {
    Page<SugarLevelRecord> getAllSugarLevelRecords(Pageable page);
    void saveSugarLevelRecord(SugarLevelRecord SugarLevelRecord);
    SugarLevelRecord getSugarLevelRecordById(long id);
    void deleteSugarLevelRecordById(long id);
}

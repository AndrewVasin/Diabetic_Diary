package com.diary.service;

import com.diary.model.SugarLevelRecord;

import java.util.List;

public interface SugarLevelService {
    List<SugarLevelRecord> getAllSugarLevelRecord();
    void saveSugarLevelRecord(SugarLevelRecord SugarLevelRecord);
    SugarLevelRecord getSugarLevelRecordById(long id);
    void deleteSugarLevelRecordById(long id);
}
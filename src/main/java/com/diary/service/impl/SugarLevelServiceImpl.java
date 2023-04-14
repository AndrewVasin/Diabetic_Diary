package com.diary.service.impl;

import com.diary.model.SugarLevelRecord;
import com.diary.repository.SugarLevelRepository;
import com.diary.service.SugarLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SugarLevelServiceImpl implements SugarLevelService {

    private final SugarLevelRepository sugarLevelRepository;

    @Autowired
    public SugarLevelServiceImpl(SugarLevelRepository sugarLevelRepository) {
        this.sugarLevelRepository = sugarLevelRepository;
    }

    public Page<SugarLevelRecord> getAllSugarLevelRecords(Pageable pageable) {
        return sugarLevelRepository.findAll(pageable);
    }

    public void saveSugarLevelRecord(SugarLevelRecord SugarLevelRecord) {
        this.sugarLevelRepository.save(SugarLevelRecord);
    }

    public SugarLevelRecord getSugarLevelRecordById(long id) {
        Optional<SugarLevelRecord> optional = sugarLevelRepository.findById(id);
        SugarLevelRecord sugarLevelRecord;
        if (optional.isPresent()) {
            sugarLevelRecord = optional.get();
        } else {
            throw new RuntimeException(" Record not found for id :: " + id);
        }
        return sugarLevelRecord;
    }

    public void deleteSugarLevelRecordById(long id) {
        this.sugarLevelRepository.deleteById(id);
    }
}

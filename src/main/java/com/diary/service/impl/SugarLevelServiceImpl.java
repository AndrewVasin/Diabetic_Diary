package com.diary.service.impl;

import com.diary.model.SugarLevelRecord;
import com.diary.repository.SugarLevelRepository;
import com.diary.service.SugarLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SugarLevelServiceImpl implements SugarLevelService {

    @Autowired
    private SugarLevelRepository sugarLevelRepository;

    @Override
    public List<SugarLevelRecord> getAllSugarLevelRecord() {
        return sugarLevelRepository.findAll();
    }

    @Override
    public void saveSugarLevelRecord(SugarLevelRecord SugarLevelRecord) {
        this.sugarLevelRepository.save(SugarLevelRecord);
    }

    @Override
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

    @Override
    public void deleteSugarLevelRecordById(long id) {
        this.sugarLevelRepository.deleteById(id);
    }
}

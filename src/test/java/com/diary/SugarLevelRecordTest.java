package com.diary;

import com.diary.model.SugarLevelRecord;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SugarLevelRecordTest {
    @Test
    public void sugarLevelshouldBeBetween0And35() {
        SugarLevelRecord sugarLevelRecord = new SugarLevelRecord();
        sugarLevelRecord.setSugarLevel((float) -1.0);

        Assertions.assertEquals(-1.0, sugarLevelRecord.getSugarLevel(), 1e-9);
    }
}

package com.diary.controller;

import com.diary.model.SugarLevelRecord;
import com.diary.service.impl.SugarLevelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class SugarLevelController {

    public final String TITLE = "title";
    public final String TITLE_MAIN = "Дневник | Сахарный диабет";

    @Autowired
    private SugarLevelServiceImpl sugarLevelService;

    @GetMapping("/index")
    public String viewIndexPage(Model model) {
        model.addAttribute(TITLE, TITLE_MAIN);
        return "index";
    }

    @GetMapping("/level/page{page}")
    public String showAllSugarLevelRecordsWithPagesNext(Model model,
                                                    @PathVariable(value = "page") int page,
                                                    @RequestParam(defaultValue = "5") int size) {
        Pageable paging = PageRequest.of(page - 1, size);

        Page<SugarLevelRecord> pageRecords = sugarLevelService.getAllSugarLevelRecords(paging);
        List<SugarLevelRecord> sugarRecordsList = pageRecords.getContent();

        model.addAttribute(TITLE, "Страница");
        model.addAttribute("sugarRecordsList", sugarRecordsList);
        model.addAttribute("currentPage", pageRecords.getNumber() + 1);
        model.addAttribute("totalItems", pageRecords.getTotalElements());
        model.addAttribute("totalPages", pageRecords.getTotalPages());
        model.addAttribute("pageSize", size);

        return "main_page";
    }


    // display list of sugar level
    @GetMapping("/level1")
    public String showAllSugarLevelRecords(Model model) {
        model.addAttribute(TITLE, TITLE_MAIN);
        model.addAttribute("listSugarLevelRecord", sugarLevelService.getAllSugarLevelRecords(Pageable.unpaged()));
        return "main";
    }

    @GetMapping("/showNewSugarLevelRecordForm")
    public String showNewSugarLevelRecordForm(Model model) {
        // create model attribute to bind form data
        SugarLevelRecord sugarLevelRecord = new SugarLevelRecord();
        model.addAttribute("sugarLevelRecord", sugarLevelRecord);
        return "new_record";
    }

    @PostMapping("/saveSugarLevelRecord")
    public String saveSugarLevelRecord(@Valid @ModelAttribute("sugarLevelRecord") SugarLevelRecord sugarLevelRecord,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new_record";
        }
        // save sugarLevelRecord to database
        sugarLevelService.saveSugarLevelRecord(sugarLevelRecord);

        return "redirect:/level/page1";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {

        // get sugarLevelRecord from the service
        SugarLevelRecord sugarLevelRecord = sugarLevelService.getSugarLevelRecordById(id);

        // set sugarLevelRecord as a model attribute to pre-populate the form
        model.addAttribute("sugarLevelRecord", sugarLevelRecord);
        return "update_record";
    }

    @GetMapping("/deleteSugarLevelRecord/{id}")
    public String deleteSugarLevelRecord(@PathVariable(value = "id") long id) {

        // call delete sugarLevelRecord method
        this.sugarLevelService.deleteSugarLevelRecordById(id);
        return "redirect:/";
    }
}

package com.diary.controller;

import com.diary.model.SugarLevelRecord;
import com.diary.service.impl.SugarLevelServiceImpl;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Optional;

@Slf4j
@Controller
public class SugarLevelController {

    public final String TITLE = "title";
    public final String TITLE_MAIN = "Дневник | Сахарный диабет";

    @Autowired
    private SugarLevelServiceImpl sugarLevelService;

    @GetMapping(value = {"/", "/index"})
    public String viewIndexPage(Model model) {
        model.addAttribute(TITLE, TITLE_MAIN);
        return "index";
    }

    @GetMapping(value = {"/level", "/level?page={page}&size={size}"})
    public String showAllSugarLevelRecordsWithPages(Model model,
                                    @RequestParam("page") @PathVariable("page") Optional<Integer> page,
                                    @RequestParam("size") @PathVariable("size") Optional<Integer> pageSizes) {
        int currentPage = page.orElse(1);
        int pageSize = pageSizes.orElse(3);
        Pageable paging = PageRequest.of(currentPage - 1, pageSize);

        Page<SugarLevelRecord> pageRecords = sugarLevelService.getAllSugarLevelRecords(paging);
        List<SugarLevelRecord> sugarRecordsList = pageRecords.getContent();
        if (currentPage > pageRecords.getTotalPages()) {
            return "error/404";
        }

        model.addAttribute(TITLE, "Страница " + (pageRecords.getNumber() + 1));
        model.addAttribute("sugarRecordsList", sugarRecordsList);
        model.addAttribute("currentPage", pageRecords.getNumber() + 1);
        model.addAttribute("totalItems", pageRecords.getTotalElements());
        model.addAttribute("totalPages", pageRecords.getTotalPages());
        model.addAttribute("size", pageRecords.getSize());
        //log.info("size? - " + pageRecords.getSize());

        return "main_2";
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

        return "redirect:/level";
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
        return "redirect:/level";
    }

}

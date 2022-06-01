package com.diary.controller;

import com.diary.model.SugarLevelRecord;
import com.diary.service.SugarLevelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class SugarLevelController {

    @Autowired
    private SugarLevelService sugarLevelService;

    // display list of sugar level
    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listSugarLevelRecord", sugarLevelService.getAllSugarLevelRecord());
        return "index";
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

        return "redirect:/";
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

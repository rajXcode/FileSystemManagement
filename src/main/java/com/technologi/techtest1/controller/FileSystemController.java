package com.technologi.techtest1.controller;/*
 * @created 22/02/2021 - 17:27
 * @project FileSystemManagement
 * @author rajender
 * rajender created on 22/02/2021 inside the package - com.technologi.techtest1.controller
 */

import com.technologi.techtest1.model.Contact;
import com.technologi.techtest1.model.FileSystem;
import com.technologi.techtest1.repository.FileSystemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @FileSystemController handle client requests
 */

@Controller
public class FileSystemController {

    @Autowired
    FileSystemRepository repository;

    @GetMapping("/")
    public String getForm(Contact filesystemdto) {
        return "index";
    }

    @PostMapping("/")
    public String getFilesOrFolders(@Valid Contact filesystemdto, Errors errors, Model model) {
        if (null == errors || errors.getErrorCount() <= 0) {
            List<FileSystem> listOfFileAndFolders = new ArrayList<>();
            try {
                listOfFileAndFolders = repository.getFilesOrFoldersByRelativePath(filesystemdto.getSearch());
                model.addAttribute("listFilesFolders", listOfFileAndFolders);
            } catch (Exception e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
                model.addAttribute("successMsg", "No Results Found");
            }
        }
        return "index";

    }

}
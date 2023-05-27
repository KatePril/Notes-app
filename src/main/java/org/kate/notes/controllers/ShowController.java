package org.kate.notes.controllers;

import lombok.NoArgsConstructor;
import org.kate.notes.domain.Note;
import org.kate.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@NoArgsConstructor

@Controller
@RequestMapping("/show")
public class ShowController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping
    public String getForm(Model model){
        addAttributes(model);
        return "show-form";
    }


    private void addAttributes(Model model) {
        model.addAttribute("notes", noteRepository.findAll(Sort.by("created").descending()));
    }
}

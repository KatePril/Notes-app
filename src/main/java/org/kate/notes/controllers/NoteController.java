package org.kate.notes.controllers;

import lombok.NoArgsConstructor;
import org.kate.notes.domain.Note;
import org.kate.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@NoArgsConstructor

@Controller
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping
    public String getForm(Model model){
        addAttributes(model, new Note());
        return "notes-form";
    }

    @PostMapping
    public String submit(Model model, Note note) {
        if (note.getName().isEmpty()) {
            addAttributes(model, note);
            model.addAttribute("errorName", "Please enter name of your note");
            return "notes-form";
        }
        if (note.getText().isEmpty()) {
            addAttributes(model, note);
            model.addAttribute("errorText", "Please enter text of your note");
            return "notes-form";
        }
        if (note.getPriority() == null) {
            addAttributes(model, note);
            model.addAttribute("errorPriority", "Please choose priority of your note");
            return "notes-form";
        }
        note.setCreated(LocalDateTime.now());
        noteRepository.save(note);
        return "redirect:/notes";
    }

    private void addAttributes(Model model, Note note) {
        model.addAttribute("note", note);
        model.addAttribute("priorityTypes", Note.Priority.values());
        model.addAttribute("notes", noteRepository.findAll());
    }
}

package com.example.markdownnotes.controller;

import com.example.markdownnotes.model.Note;
import com.example.markdownnotes.service.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public String listNotes(@RequestParam(required = false) String keyword, Model model) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("notes", noteService.searchNotes(keyword));
        } else {
            model.addAttribute("notes", noteService.getAllNotes());
        }
        model.addAttribute("keyword", keyword);
        return "index";
    }

    @GetMapping("/new")
    public String newNoteForm(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("note", new Note());
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("notes", noteService.searchNotes(keyword));
        } else {
            model.addAttribute("notes", noteService.getAllNotes());
        }
        model.addAttribute("keyword", keyword);
        return "editor";
    }

    @PostMapping("/save")
    public String saveNote(@ModelAttribute("note") Note note) {
        noteService.saveNote(note);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editNoteForm(@PathVariable Long id, @RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("note", noteService.getNoteById(id));
        if (keyword != null && !keyword.trim().isEmpty()) {
            model.addAttribute("notes", noteService.searchNotes(keyword));
        } else {
            model.addAttribute("notes", noteService.getAllNotes());
        }
        model.addAttribute("keyword", keyword);
        return "editor";
    }

    @GetMapping("/delete/{id}")
    public String deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return "redirect:/";
    }
}

package com.example.markdownnotes.service;

import com.example.markdownnotes.model.Note;
import com.example.markdownnotes.repository.NoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> searchNotes(String keyword) {
        return noteRepository.findByTitleContainingIgnoreCaseOrContentContainingIgnoreCase(keyword, keyword);
    }

    public Note getNoteById(Long id) {
        return noteRepository.findById(id).orElse(new Note());
    }

    public void saveNote(Note note) {
        noteRepository.save(note);
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }
}

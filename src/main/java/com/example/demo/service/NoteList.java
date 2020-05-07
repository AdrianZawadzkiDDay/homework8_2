package com.example.demo.service;

import com.example.demo.model.Note;
import com.example.demo.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NoteList {

    private List<Note> noteList;
    private NoteRepository noteRepository;

    @Autowired
    public NoteList(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
        noteList = new ArrayList<>();
        getLikeTitle("notatki");
    }

    public List<Note> getLikeTitle(String searchesTitle){
        return noteRepository.findByTitleContains(searchesTitle);
    }


    public List<Note> getNoteList() {
        return noteRepository.findAll();
    }

    public void add(Note note) {
        noteRepository.save(note);
    }

    public void update(Note note){
        noteRepository.saveAndFlush(note);
    }

    public void delete(Long id){
        noteRepository.deleteById(id);
    }
}

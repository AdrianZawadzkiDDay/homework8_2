package com.example.demo.controller;

import com.example.demo.model.Note;
import com.example.demo.service.NoteList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NoteController {

    private NoteList noteList;

    public NoteController(NoteList noteList) {
        this.noteList = noteList;
    }

    @GetMapping
    public String getNote(Model model){
        model.addAttribute("notes", noteList.getNoteList());
        model.addAttribute("modNote", new Note());
        return "main";
    }


    @GetMapping("/modification/{id}")
    public String modNote(@ModelAttribute Note note, @PathVariable Long id){
        note.setId(id);
        noteList.update(note);
        return "redirect:/notes";
    }

    @GetMapping("/delete/{id}")
    public String removeVehicle(@PathVariable Long id){
        noteList.delete(id);
        return "redirect:/notes";
    }

    @GetMapping("/addnote")
    public String addNew(@ModelAttribute Note note, Model model) {
        model.addAttribute("newNote", new Note());
        return "putnewnote";
    }

    @GetMapping("/addnote/add")
    public String addNewNote(@ModelAttribute Note note, Model model) {
        model.addAttribute("newNote", new Note());
        noteList.add(note);
        return "redirect:/notes/addnote";
    }
}

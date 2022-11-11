package com.gsilva.easynotes.controller;

import com.gsilva.easynotes.dto.EntryPointDto;
import com.gsilva.easynotes.dto.NoteForm;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class RootController {

    @GetMapping
    public EntryPointDto root() {
        EntryPointDto dto = new EntryPointDto();
        dto.add(linkTo(methodOn(NotesController.class).getAllNotes()).withRel("all-notes-get").withType("GET"));
        dto.add(linkTo(methodOn(NotesController.class).updateNote(null, null)).withRel("notes-put").withType("PUT"));
        dto.add(linkTo(methodOn(NotesController.class).createNote(new NoteForm())).withRel("notes-post").withType("POST"));
        dto.add(linkTo(methodOn(NotesController.class).deleteNote(null)).withRel("notes-delete").withType("DELETE"));
        dto.add(linkTo(methodOn(NotesController.class).getNoteById(null)).withRel("notes-get").withType("GET"));
        return dto;
    }

}

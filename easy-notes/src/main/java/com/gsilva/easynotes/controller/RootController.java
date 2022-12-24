package com.gsilva.easynotes.controller;

import com.gsilva.easynotes.dto.EntryPointDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RootController {

    @GetMapping
    public EntryPointDto root() {
        EntryPointDto dto = new EntryPointDto();
        dto.add(linkTo(methodOn(NotesController.class).getAllNotes()).withRel("Todas as notas"));
        return dto;
    }

}

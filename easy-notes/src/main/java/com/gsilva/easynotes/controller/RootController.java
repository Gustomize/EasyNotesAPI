package com.gsilva.easynotes.controller;

import com.gsilva.easynotes.dto.EntryPointDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1")
public class RootController {

    @GetMapping
    public EntryPointDto root() {
        return new EntryPointDto()
                .add(linkTo(methodOn(NotesController.class).getAllNotes())
                        .withRel("Todas as notas"));
    }

}

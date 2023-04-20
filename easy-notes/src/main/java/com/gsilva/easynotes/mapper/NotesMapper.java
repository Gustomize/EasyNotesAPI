package com.gsilva.easynotes.mapper;

import com.gsilva.easynotes.controller.NotesController;
import com.gsilva.easynotes.dto.NoteDetailsDto;
import com.gsilva.easynotes.dto.NoteDto;
import com.gsilva.easynotes.dto.NoteForm;
import com.gsilva.easynotes.model.Note;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NotesMapper {

    private final ModelMapper modelMapper;

    public NotesMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public NoteDto toDto(Note note) {
        return modelMapper.map(note, NoteDto.class)
                .add(linkTo(methodOn(NotesController.class).getNoteById(note.getId())).withSelfRel())
                .add(linkTo(methodOn(NotesController.class).getAllNotes()).withRel("all-notes"));
    }

    public NoteDetailsDto toDetails(Note note) {
        return modelMapper.map(note, NoteDetailsDto.class)
                .add(linkTo(methodOn(NotesController.class).getNoteById(note.getId())).withSelfRel())
                .add(linkTo(methodOn(NotesController.class).getAllNotes()).withRel("todasAsNotas"));
    }

    public Note toModel(NoteForm form) {
        return modelMapper.map(form, Note.class);
    }

}

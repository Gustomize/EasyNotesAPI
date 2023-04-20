package com.gsilva.easynotes.controller;

import com.gsilva.easynotes.dto.NoteDetailsDto;
import com.gsilva.easynotes.dto.NoteDto;
import com.gsilva.easynotes.dto.NoteForm;
import com.gsilva.easynotes.mapper.NotesMapper;
import com.gsilva.easynotes.model.Note;
import com.gsilva.easynotes.service.NotesService;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/v1/notes")
public class NotesController {

    private final NotesMapper notesMapper;
    private final NotesService notesService;

    public NotesController(NotesMapper notesMapper, NotesService notesService) {
        this.notesMapper = notesMapper;
        this.notesService = notesService;
    }

    @GetMapping
    public ResponseEntity<CollectionModel<NoteDto>> getAllNotes() {
        List<NoteDto> dtoList = notesService.getAllNotes()
                .stream()
                .map(notesMapper::toDto)
                .collect(Collectors.toList());
        Link todasAsNotasLink = linkTo(methodOn(NotesController.class)
                .getAllNotes())
                .withSelfRel();
        return ResponseEntity.ok(CollectionModel.of(dtoList, todasAsNotasLink));
    }

    @PostMapping
    public ResponseEntity<NoteDto> createNote(@Valid @RequestBody NoteForm form) {
        Note note = notesService.createNote(notesMapper.toModel(form));
        NoteDto dto = notesMapper.toDto(note);
        URI uri = dto.getLink("self")
                .orElseThrow(NullPointerException::new)
                .toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteDetailsDto> getNoteById(@PathVariable(value = "id") Long id) {
        return notesService.getNoteById(id)
                .map(value -> ResponseEntity.ok(notesMapper.toDetails(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteDto> updateNote(@PathVariable(value = "id") Long id, @Valid @RequestBody NoteForm form) {
        return ResponseEntity.ok(notesMapper.toDto(notesService.updateNote(id, form)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable(value = "id") Long id) {
        notesService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

}

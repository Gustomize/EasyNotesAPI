package com.gsilva.easynotes.controller;

import com.gsilva.easynotes.exception.ResourceNotFoundException;
import com.gsilva.easynotes.model.Note;
import com.gsilva.easynotes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.text.MessageFormat.format;

@RestController
@RequestMapping("/api")
public class NotesController {

    private final NoteRepository repository;
    private static final Logger LOG = LoggerFactory.getLogger(NotesController.class);

    @Autowired
    public NotesController(NoteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/notes")
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    @PostMapping("/notes")
    public Note createNote(@Valid @RequestBody Note note) {
        LOG.info(format("Salvando nota '{}'", note.getTitle()));
        return repository.save(note);
    }

    @GetMapping("/notes/{id}")
    public Note getNoteById(@PathVariable(value = "id") long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
    }

    @PutMapping("/notes/{id}")
    public Note updateNote(@PathVariable(value = "id") long id, @Valid Note noteDetails) {
        Note note = getNoteById(id);
        note.setTitle(noteDetails.getTitle());
        note.setContent(noteDetails.getContent());

        LOG.info(format("Alterando nota '{0}' para '{1}'", note.getTitle(), noteDetails.getContent()));

        return repository.save(note);
    }

    @DeleteMapping("/notes/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable(value = "id") long id) {
        Note note = getNoteById(id);
        repository.delete(note);

        LOG.info(format("Removendo nota '{}'", note.getTitle()));

        return ResponseEntity.ok().build();
    }
}

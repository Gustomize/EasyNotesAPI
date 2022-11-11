package com.gsilva.easynotes.service;

import com.gsilva.easynotes.controller.NotesController;
import com.gsilva.easynotes.dto.NoteForm;
import com.gsilva.easynotes.model.Note;
import com.gsilva.easynotes.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotesService {

    private static final Logger LOG = LoggerFactory.getLogger(NotesService.class);

    private final NoteRepository repository;

    @Autowired
    public NotesService(NoteRepository repository) {
        this.repository = repository;
    }

    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    public Note createNote(Note note) {
        LOG.info("Salvando nota '{}'", note.getTitle());
        note.setCreatedAt(new Date());
        note.setUpdatedAt(note.getCreatedAt());
        return repository.save(note);
    }

    public Optional<Note> getNoteById(Long id) {
        return repository.findById(id);
    }

    public Note updateNote(Long id, NoteForm form) {
        Note note = repository.findById(id).orElse(null);

        if (note == null) {
            throw new NullPointerException();
        }

        note.setTitle(form.getTitle());
        note.setContent(form.getContent());
        note.setUpdatedAt(new Date());

        LOG.info("Alterando nota {} para {}", note.getTitle(), form.getContent());

        return repository.save(note);
    }

    public void deleteNote(Long id) {
        Note note = repository.findById(id).orElse(null);

        if (note == null) {
            throw new NullPointerException();
        }

        LOG.info("Removendo nota {}", note.getTitle());

        repository.delete(note);
    }

}

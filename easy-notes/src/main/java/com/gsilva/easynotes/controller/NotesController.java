package com.gsilva.easynotes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gsilva.easynotes.exception.ResourceNotFoundException;
import com.gsilva.easynotes.model.Note;
import com.gsilva.easynotes.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NotesController {

	private NoteRepository repository;

	public NotesController(NoteRepository repository) {
		this.repository = repository;
	}

	@GetMapping("/notes")
	public List<Note> getAllNotes() {
		return repository.findAll();
	}

	@PostMapping("/notes")
	public Note createNote(@Valid @RequestBody Note note) {
		return repository.save(note);
	}

	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") long id) {
		return repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
	}

	@PutMapping("/notes/{id}")
	public Note updateNote(@PathVariable(value = "id") long id, @Valid Note noteDetails) {
		Note note = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());
		
		Note updatedNote = repository.save(note);
		return updatedNote;
	}
	
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") long id) {
		Note note = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		
		repository.delete(note);
		return ResponseEntity.ok().build();
	}
}

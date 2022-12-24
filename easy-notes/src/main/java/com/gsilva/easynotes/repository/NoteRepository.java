package com.gsilva.easynotes.repository;

import com.gsilva.easynotes.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}

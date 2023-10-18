package org.rest.controller;

import lombok.RequiredArgsConstructor;
import org.rest.controller.model.NotesModelAssembler;
import org.rest.dto.Note;
import org.rest.service.NotesService;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService notesService;

    private final NotesModelAssembler notesModelAssembler;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Note>> get(@PathVariable("id") long noteId) {
        return ResponseEntity.ok(notesModelAssembler.toModel(notesService.findById(noteId)));
    }
}

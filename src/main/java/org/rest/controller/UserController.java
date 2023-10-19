package org.rest.controller;

import lombok.RequiredArgsConstructor;
import org.rest.controller.model.NotesModelAssembler;
import org.rest.controller.model.UserModelAssembler;
import org.rest.dto.Note;
import org.rest.dto.User;
import org.rest.service.NotesService;
import org.rest.service.UserService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final NotesService notesService;

    private final UserModelAssembler userModelAssembler;
    private final NotesModelAssembler notesModelAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<User>> add(@RequestBody User user) {
        User createdUser = userService.create(user);
        return ResponseEntity.created(linkTo(methodOn(UserController.class).get(createdUser.getId())).toUri())
                .body(userModelAssembler.toModel(createdUser));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<User>> get(@PathVariable("id") long userId) {
        return ResponseEntity.ok(userModelAssembler.toModel(userService.findById(userId)));
    }

    @PostMapping("/{id}/notes")
    public ResponseEntity<EntityModel<Note>> addNote(@PathVariable("id") long userId, @RequestBody Note note) {
        Note createdNote = notesService.create(userId, note);
        return ResponseEntity.created(linkTo(methodOn(NotesController.class).get(createdNote.getId())).toUri())
                .body(notesModelAssembler.toModel(createdNote));
    }

    @GetMapping("/{id}/notes")
    public ResponseEntity<CollectionModel<EntityModel<Note>>> getNotes(@PathVariable("id") long userId) {
        return ResponseEntity.ok(notesModelAssembler.toCollectionModel(userService.findById(userId).getNotes()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void remove(@PathVariable("id") long userId) {
        userService.removeById(userId);
    }
}

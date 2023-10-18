package org.rest.controller.model;

import org.rest.controller.NotesController;
import org.rest.controller.UserController;
import org.rest.dto.Note;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class NotesModelAssembler implements RepresentationModelAssembler<Note, EntityModel<Note>> {
    @Override
    public EntityModel<Note> toModel(Note note) {
        return EntityModel.of(note,
                WebMvcLinkBuilder.linkTo(methodOn(NotesController.class).get(note.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(methodOn(UserController.class).get(note.getUser().getId())).withRel("user")
        );
    }
}

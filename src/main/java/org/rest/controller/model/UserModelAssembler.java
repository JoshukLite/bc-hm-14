package org.rest.controller.model;

import org.rest.controller.UserController;
import org.rest.dto.User;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {
    @Override
    public EntityModel<User> toModel(User user) {
        EntityModel<User> model = EntityModel.of(user,
                linkTo(methodOn(UserController.class).get(user.getId())).withSelfRel()
        );
        if (!user.getNotes().isEmpty()) {
            model.add(linkTo(methodOn(UserController.class).getNotes(user.getId())).withRel("notes"));
        }
        return model;
    }
}

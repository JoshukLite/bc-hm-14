package org.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class User {
    @JsonProperty(access = READ_ONLY)
    private long id;
    private String firstName;
    private String lastName;
    @JsonIgnore
    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private List<Note> notes = new ArrayList<>();

    public void addNote(Note note) {
        note.setUser(this);
        notes.add(note);
    }

    public List<Note> getNotes() {
        return Collections.unmodifiableList(notes);
    }
}

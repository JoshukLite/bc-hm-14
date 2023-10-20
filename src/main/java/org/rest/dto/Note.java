package org.rest.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY;

@Data
public class Note {
    @JsonProperty(access = READ_ONLY)
    private long id;
    private String title;
    private String text;
    @JsonIgnore
    private User user;
}

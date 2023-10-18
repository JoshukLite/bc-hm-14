package org.rest.service;

import lombok.RequiredArgsConstructor;
import org.rest.dto.Note;
import org.rest.dto.User;
import org.rest.repository.NotesRepository;
import org.rest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotesService {

    private final UserRepository userRepository;

    private final NotesRepository notesRepository;

    public Note create(long userId, Note note) {
        User user = userRepository.findById(userId).orElseThrow();
        Note createdNote = notesRepository.create(note);
        user.addNote(createdNote);
        return createdNote;
    }

    public Note findById(long id) {
        return notesRepository.findById(id)
                .orElseThrow();
    }
}

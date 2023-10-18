package org.rest.service;

import lombok.RequiredArgsConstructor;
import org.rest.dto.User;
import org.rest.repository.NotesRepository;
import org.rest.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NotesRepository notesRepository;

    public User create(User user) {
        return userRepository.create(user);
    }

    public User findById(long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    public void removeById(long id) {
        User user = userRepository.findById(id).orElseThrow();
        userRepository.removeById(user.getId());
        user.getNotes().forEach(note -> notesRepository.removeById(note.getId()));
    }
}

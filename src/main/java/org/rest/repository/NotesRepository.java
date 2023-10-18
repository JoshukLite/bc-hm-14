package org.rest.repository;

import org.rest.dto.Note;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class NotesRepository {

    private static final AtomicLong noteIdCounter = new AtomicLong();
    private static final ConcurrentHashMap<Long, Note> noteMap = new ConcurrentHashMap<>();

    public Note create(Note note) {
        note.setId(noteIdCounter.incrementAndGet());
        noteMap.put(note.getId(), note);
        return note;
    }

    public Optional<Note> findById(long id) {
        return Optional.ofNullable(noteMap.get(id));
    }

    public void removeById(long id) {
        Optional.ofNullable(noteMap.remove(id))
                .ifPresent(note -> note.setUser(null));
    }
}

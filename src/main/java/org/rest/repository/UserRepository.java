package org.rest.repository;

import org.rest.dto.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {

    private static final AtomicLong userIdCounter = new AtomicLong();
    private static final ConcurrentHashMap<Long, User> userMap = new ConcurrentHashMap<>();

    public User create(User user) {
        user.setId(userIdCounter.incrementAndGet());
        userMap.put(user.getId(), user);
        return user;
    }

    public Optional<User> findById(long id) {
        return Optional.ofNullable(userMap.get(id));
    }

    public void removeById(long id) {
        userMap.remove(id);
    }
}

package org.photoalbum.photoalbum.repository;

import org.photoalbum.photoalbum.model.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserMessageRepository extends JpaRepository<UserMessage, Integer> {
}

package com.urieletyoh.kitty.repository;

import com.urieletyoh.kitty.entity.PrivateMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {
}

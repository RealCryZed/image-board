package com.example.imageboard.repository;

import com.example.imageboard.model.AnonymousUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnonymousUserRepository extends JpaRepository<AnonymousUser, Long> {
}

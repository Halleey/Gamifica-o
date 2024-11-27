package com.presente.confeitaria.repositories;

import com.presente.confeitaria.entities.UsersInteractions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersInteractionsRepository extends JpaRepository<UsersInteractions, Long> {

    List<UsersInteractions> findByUserId(Long userId);
}

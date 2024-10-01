package com.presente.confeitaria.repositories;

import com.presente.confeitaria.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {
}

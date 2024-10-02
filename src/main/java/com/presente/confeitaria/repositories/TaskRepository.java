package com.presente.confeitaria.repositories;

import com.presente.confeitaria.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

package com.presente.confeitaria.services;


import com.presente.confeitaria.dtos.TaskRequestDTO;
import com.presente.confeitaria.entities.Task;
import com.presente.confeitaria.repositories.TaskRepository;
import org.springframework.stereotype.Service;

@Service
public class TaskService {


    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public void saveTask(TaskRequestDTO requestDTO) {

        Task task = new Task();
        task.setName(requestDTO.getName());
        task.setDescription(requestDTO.getDescription());
        task.setDifficulty(requestDTO.getDifficulty());
        repository.save(task);
    }

}

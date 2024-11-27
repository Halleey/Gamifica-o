package com.presente.confeitaria.services;
import com.presente.confeitaria.dtos.task.TaskRequestDTO;
import com.presente.confeitaria.dtos.task.TaskResponseDTO;
import com.presente.confeitaria.entities.Task;
import com.presente.confeitaria.repositories.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


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

    public void removeTask(Long id) {
        repository.deleteById(id);
    }

    public List<TaskResponseDTO> getAllTask() {
        return  repository.findAll().stream().map(response -> new TaskResponseDTO(response.getName(), response.getDescription(), response.getDifficulty()))
                .collect(Collectors.toList());

    }
}

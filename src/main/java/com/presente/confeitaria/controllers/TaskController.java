package com.presente.confeitaria.controllers;

import com.presente.confeitaria.dtos.task.TaskRequestDTO;
import com.presente.confeitaria.services.TaskService;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/task")
@RestController
@CrossOrigin("http://localhost:3000")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }


    @PostMapping
    public void RegisterTask(@RequestBody TaskRequestDTO requestDTO) {

         service.saveTask(requestDTO);
    }
}

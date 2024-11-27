package com.presente.confeitaria.controllers;

import com.presente.confeitaria.entities.UsersInteractions;
import com.presente.confeitaria.services.PreferencesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/api/interactions")
public class PreferencesController {


    private final PreferencesService interactionsService;

    public PreferencesController(PreferencesService interactionsService) {
        this.interactionsService = interactionsService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<UsersInteractions>> getInteractions(@PathVariable Long userId) {
        return ResponseEntity.ok(interactionsService.getInteractionsByUserId(userId));
    }

    @GetMapping("/{userId}/most-accessed")
    public ResponseEntity<Map<String, Long>> getMostAccessedEndpoints(@PathVariable Long userId) {
        return ResponseEntity.ok(interactionsService.getMostAccessedEndpoints(userId));
    }
}
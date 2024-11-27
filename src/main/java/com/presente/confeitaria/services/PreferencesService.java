package com.presente.confeitaria.services;

import com.presente.confeitaria.entities.UsersInteractions;
import com.presente.confeitaria.repositories.UsersInteractionsRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PreferencesService {

    private final UsersInteractionsRepository repository;

    public PreferencesService(UsersInteractionsRepository repository) {
        this.repository = repository;
    }

    public void saveInteraction(Long userId,  String endPoint,  String method) {
        UsersInteractions interaction = new UsersInteractions();
        interaction.setUserId(userId);
        interaction.setHttpMethod(method);
        interaction.setEndPoint(endPoint);
        interaction.setTimestamp(LocalDateTime.now());
        repository.save(interaction);
    }

    public List<UsersInteractions> getInteractionsByUserId(Long userId) {
        return repository.findByUserId(userId);
    }

    public Map<String, Long> getMostAccessedEndpoints(Long userId) {
        List<UsersInteractions> interactions = getInteractionsByUserId(userId);

        // Agrupa por endpoint e conta as ocorrências.
        return interactions.stream()
                .collect(Collectors.groupingBy(UsersInteractions::getEndPoint, Collectors.counting()));
    }
}

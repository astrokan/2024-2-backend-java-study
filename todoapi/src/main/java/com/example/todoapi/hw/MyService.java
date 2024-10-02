package com.example.todoapi.hw;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
@Getter
public class MyService {
    private final MyRepository myRepository;

    public void serviceController() {
        System.out.println("service");
        myRepository.repositoryController();
    }
}

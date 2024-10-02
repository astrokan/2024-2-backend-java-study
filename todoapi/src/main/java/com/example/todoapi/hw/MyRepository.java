package com.example.todoapi.hw;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MyRepository {
    public void repositoryController() {
        System.out.println("repository");
    }
}

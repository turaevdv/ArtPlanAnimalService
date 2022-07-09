package ru.turaev.animalservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;
import ru.turaev.animalservice.service.AuthorizationService;

@RequiredArgsConstructor
public class AuthorizationServiceImpl implements AuthorizationService {
    private final WebClient webClient;

    @Value("${jwt.header}")
    private String HEADER;

    @Override
    public boolean isTokenValid(String token) {
        try {
            return webClient.get()
                    .uri("/auth/token-verification")
                    .header(HEADER, token)
                    .retrieve()
                    .bodyToMono(Boolean.class)
                    .block();
        } catch (Exception e) {
            return false;
        }
    }
}

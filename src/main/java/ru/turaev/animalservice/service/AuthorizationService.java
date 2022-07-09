package ru.turaev.animalservice.service;

public interface AuthorizationService {
    boolean isTokenValid(String token);
}

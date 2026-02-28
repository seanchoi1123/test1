package com.example.mentalmath.dto;

public record CheckResponse(boolean correct, int expected, String message) {
}

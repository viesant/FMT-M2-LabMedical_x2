package br.viesant.labmedical_x2.controllers.DTO;

public record TokenResponse(
        String token,
        Long expiresAt
) {}

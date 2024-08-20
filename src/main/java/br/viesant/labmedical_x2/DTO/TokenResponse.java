package br.viesant.labmedical_x2.DTO;

public record TokenResponse(
        String token,
        Long expiresAt
) {}

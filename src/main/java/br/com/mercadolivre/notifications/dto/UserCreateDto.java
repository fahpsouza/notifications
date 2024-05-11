package br.com.mercadolivre.notifications.dto;

import jakarta.validation.constraints.NotNull;

public record UserCreateDto(
        @NotNull
        String name) {
}

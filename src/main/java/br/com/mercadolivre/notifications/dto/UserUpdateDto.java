package br.com.mercadolivre.notifications.dto;

import jakarta.validation.constraints.NotNull;

public record UserUpdateDto(
        @NotNull
        Long id) {
}

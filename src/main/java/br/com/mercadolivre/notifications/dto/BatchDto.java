package br.com.mercadolivre.notifications.dto;

import jakarta.validation.constraints.NotBlank;

public record BatchDto(@NotBlank Long jobRunEveryFewSeconds) {
}

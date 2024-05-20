package br.com.mercadolivre.notifications.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReceiveNotificationDto(
        @NotBlank
        String message,

        @NotBlank
        String email,

        @NotNull
        Long userId,

        @NotBlank
        String notificationType) {
}

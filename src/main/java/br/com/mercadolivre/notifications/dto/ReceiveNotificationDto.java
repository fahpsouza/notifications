package br.com.mercadolivre.notifications.dto;

import jakarta.validation.constraints.NotBlank;

public record ReceiveNotificationDto(
        @NotBlank
        String message,

        @NotBlank
        String UserName,

        @NotBlank
        String productDescription) {
}

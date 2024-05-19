package br.com.mercadolivre.notifications.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SendNotificationToWebfluxDto {
        private String message;
        private String notificationType;
        private String userId;

}

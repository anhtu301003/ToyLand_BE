package com.toyland.notification_service.controller;

import com.toyland.event.dto.NotificationEvent;
import com.toyland.notification_service.dto.request.Recipient;
import com.toyland.notification_service.dto.request.SendEmailRequest;
import com.toyland.notification_service.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class NotificationController {
    EmailService emailService;
    @KafkaListener(topics = "notification-delivery")
    public void listenNotificationDelivery(NotificationEvent message) {
        log.info("message received: {}", message);
        emailService.sendEmail(
                SendEmailRequest.builder()
                        .to(
                                Recipient.builder()
                                .email(message.getRecipient())
                                .build()
                        )
                        .subject(message.getSubject())
                        .htmlContent(message.getBody())
                        .build()
        );
    }
}

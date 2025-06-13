package com.toyland.order_service.service;

import com.toyland.event.dto.NotificationEvent;
import com.toyland.order_service.dto.response.UserEmailProfileResponse;
import com.toyland.order_service.entity.Order;
import com.toyland.order_service.repository.httpClient.UserClient;
import com.toyland.order_service.service.IService.OrderObserver;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class KafkaNotificationObserver implements OrderObserver {
    KafkaTemplate<String, Object> kafkaTemplate;
    String recipient;
    @Override
    public void update(Order order) {

        NotificationEvent notificationEvent = NotificationEvent.builder()
                .channel("EMAIL")
                .recipient(recipient)
                .subject("Trạng thái đơn hàng đã thay đổi")
                .body(String.format("Đơn hàng %s đã thay đổi trạng thái thành %s", order.getOrderId(), order.getOrderStatus()))
                .build();

        try {
            kafkaTemplate.send("notification-delivery", notificationEvent);
            log.info("Sent notification for order {} to Kafka topic 'notification-delivery'", order.getOrderId());
        } catch (Exception e) {
            log.error("Failed to send Kafka notification for order {}: {}", order.getOrderId(), e.getMessage());
        }
    }
}

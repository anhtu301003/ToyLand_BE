package com.toyland.inventory_service.service;

import com.toyland.event.dto.NotificationEvent;
import com.toyland.inventory_service.entity.Inventory;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LowStockNotificationService {

    KafkaTemplate<String, Object> kafkaTemplate;

    public void checkAndNotifyLowStock(Inventory inventory) {

        if(inventory.getQuantity() > 0 && inventory.getLowStockThreshold()>0){
            if(inventory.getQuantity() < inventory.getLowStockThreshold()){
                log.info("Low stock notification occurred{}", inventory.getProductName());

                NotificationEvent notificationEvent = NotificationEvent.builder()
                        .channel("EMAIL")
                        .recipient("admin2003@yopmail.com")
                        .subject("Hàng trong kho thấp" + inventory.getProductName() + inventory.getProductId())
                        .body("Hàng trong kho thấp" + inventory.getProductName() + inventory.getProductId())
                        .build();

                kafkaTemplate.send("notification-delivery", notificationEvent);
            }
        }
    }
}

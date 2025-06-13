package com.toyland.order_service.service;

import com.toyland.event.dto.ProductItemEvent;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class OrderItemListerner {
    KafkaTemplate<String, Object> kafkaTemplate;

    @KafkaListener(topics = "order-events",groupId = "order-group")
    public void handleOrderCreate(List<ProductItemEvent> productItems) {

    }
}

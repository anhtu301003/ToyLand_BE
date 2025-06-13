package com.toyland.inventory_service.service;

import com.toyland.inventory_service.dto.request.InventoryTransactionRequest;
import com.toyland.inventory_service.dto.response.InventoryTransactionResponse;
import com.toyland.inventory_service.entity.InventoryTransaction;
import com.toyland.inventory_service.mapper.InventoryTransactionMapper;
import com.toyland.inventory_service.repository.InventoryTransactionRepository;
import com.toyland.inventory_service.service.IService.IInventoryTransactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class InventoryTransactionService implements IInventoryTransactionService {
    InventoryTransactionRepository inventoryTransactionRepository;
    InventoryTransactionMapper inventoryTransactionMapper;

    @Override
    public InventoryTransactionResponse createInventoryTransaction(InventoryTransactionRequest request) {
        InventoryTransaction inventoryTransaction = inventoryTransactionMapper.toInventoryTransaction(request);
        return inventoryTransactionMapper.toInventoryTransactionResponse(inventoryTransactionRepository.save(inventoryTransaction));
    }

    @Override
    public String deleteInventoryTransaction(String inventoryTransactionId) {
        if(!inventoryTransactionRepository.existsById(inventoryTransactionId)) {
            return "Inventory Transaction with id " + inventoryTransactionId + " does not exist";
        }
        try{
            inventoryTransactionRepository.deleteById(inventoryTransactionId);
        }catch(Exception e){
            return e.getMessage();
        }
        return "Inventory Transaction deleted successfully";
    }
}

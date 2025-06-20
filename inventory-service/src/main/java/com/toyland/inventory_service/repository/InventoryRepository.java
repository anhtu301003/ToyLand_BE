package com.toyland.inventory_service.repository;

import com.toyland.inventory_service.entity.Inventory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, String>, JpaSpecificationExecutor<Inventory> {
    List<Inventory> findAllByProductId(String productId);

    Page<Inventory> findAll(Specification<Inventory> spec, Pageable pageable);

    Inventory findByProductId(String productId);
}

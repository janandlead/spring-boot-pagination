package com.durgasoft.spring_boot_pagination.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.durgasoft.spring_boot_pagination.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}

package com.durgasoft.spring_boot_pagination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.durgasoft.spring_boot_pagination.entity.Product;
import com.durgasoft.spring_boot_pagination.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public Page<Product> getProducts(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);

	}
}
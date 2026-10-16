package com.durgasoft.spring_boot_pagination.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgasoft.spring_boot_pagination.entity.Product;
import com.durgasoft.spring_boot_pagination.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	// Basic Pagination

	@GetMapping
	public Page<Product> getProduct(@RequestParam int page, @RequestParam int size) {

		return productService.getProducts(page, size);
	}

}

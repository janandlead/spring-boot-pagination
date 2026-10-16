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
	
	/**
	 * Handles GET requests to /api/v1/products using the required page and size
	 * query parameters. For example, page=0 and size=10 requests the first page.
	 *
	 * @param page zero-based page index; must be greater than or equal to zero
	 * @param size maximum number of products per page; must be greater than zero
	 * @return products in the requested page, together with pagination metadata
	 * @throws IllegalArgumentException if page is negative or size is not positive
	 */
	@GetMapping
	public Page<Product> getProduct(@RequestParam int page, @RequestParam int size) {

		return productService.getProducts(page, size);
	}

}

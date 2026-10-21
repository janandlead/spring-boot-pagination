package com.durgasoft.spring_boot_pagination.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	/**
	 * Returns a page ordered by an entity property, defaulting to price ascending.
	 * Equal sort values have no additional tie-breaker.
	 *
	 * @param page zero-based page index, defaulting to 0
	 * @param size positive page size, defaulting to 5
	 * @param sortBy Product property to sort by, defaulting to price
	 * @param direction desc (case-insensitive) for descending; otherwise ascending
	 * @return sorted products and pagination totals
	 * @throws IllegalArgumentException if page is negative or size is not positive
	 */
	@GetMapping("/sort")
	public Page<Product> getProductsWithSorting(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size, @RequestParam(defaultValue = "price") String sortBy,
			@RequestParam(defaultValue = "asc") String direction) {
		return productService.getProductsWithSorting(page, size, sortBy, direction);
	}

	/**
	 * Returns a page of products matching the supplied category without explicit ordering.
	 *
	 * @param category category value matched using database equality semantics
	 * @param page zero-based page index, defaulting to 0
	 * @param size positive page size, defaulting to 5
	 * @return matching products and filtered totals; content is empty if none match
	 * @throws IllegalArgumentException if page is negative or size is not positive
	 */
	@GetMapping("/category/{category}")
	public Page<Product> getProductsByCategory(@PathVariable String category,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size) {

		return productService.getProductsByCategory(category, page, size);

	}

}

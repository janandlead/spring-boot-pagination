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

	/**
	 * Retrieves a page of products by applying pagination in the database.
	 * No explicit sort is applied, so product ordering is not guaranteed.
	 * The repository may execute a count query to obtain the result totals.
	 *
	 * @param page zero-based page index; must be greater than or equal to zero
	 * @param size maximum number of products per page; must be greater than zero
	 * @return a page containing products and totals, with empty content when the
	 *         requested page is beyond the available results
	 * @throws IllegalArgumentException if page is negative or size is not positive
	 */
	public Page<Product> getProducts(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findAll(pageable);

	}
}

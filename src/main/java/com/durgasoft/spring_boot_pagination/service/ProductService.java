package com.durgasoft.spring_boot_pagination.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.durgasoft.spring_boot_pagination.entity.Product;
import com.durgasoft.spring_boot_pagination.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	/**
	 * Retrieves a page of products by applying pagination in the database. No
	 * explicit sort is applied, so product ordering is not guaranteed. The
	 * repository may execute a count query to obtain the result totals.
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

	/**
	 * Applies pagination and single-property sorting in the database. Equal sort
	 * values have no guaranteed order; unknown properties fail during query creation.
	 *
	 * @param page nonnegative, zero-based page index
	 * @param size positive maximum number of products
	 * @param sortBy nonempty Product property name, such as price or id
	 * @param direction non-null direction; only desc (case-insensitive) selects descending
	 * @return sorted page with totals, or empty content beyond the available results
	 * @throws IllegalArgumentException if pagination bounds or sort property text are invalid
	 */
	public Page<Product> getProductsWithSorting(int page, int size, String sortBy, String direction) {

		Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();

		Pageable pageable = PageRequest.of(page, size, sort);
		return productRepository.findAll(pageable);
	}

	/**
	 * Filters products by category before applying database pagination. No explicit
	 * sorting is applied, and totals refer only to matching products.
	 *
	 * @param category category value matched using database equality semantics
	 * @param page nonnegative, zero-based page index
	 * @param size positive maximum number of products
	 * @return matching page, with empty content when no rows match or the page is out of range
	 * @throws IllegalArgumentException if page is negative or size is not positive
	 */
	public Page<Product> getProductsByCategory(String category, int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return productRepository.findByCategory(category, pageable);

	}
}

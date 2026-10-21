package com.durgasoft.spring_boot_pagination.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.durgasoft.spring_boot_pagination.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	/**
	 * Finds products by category equality using a Spring Data derived query.
	 *
	 * @param category category to match; case sensitivity follows database semantics
	 * @param pageable pagination and optional ordering to apply
	 * @return matching products and filtered totals, possibly using a separate count query
	 */
	Page<Product> findByCategory(String category, Pageable pageable);
}

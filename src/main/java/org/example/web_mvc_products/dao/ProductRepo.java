package org.example.web_mvc_products.dao;

import org.example.web_mvc_products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {
}

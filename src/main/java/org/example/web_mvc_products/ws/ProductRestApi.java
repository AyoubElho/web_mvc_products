package org.example.web_mvc_products.ws;

import org.example.web_mvc_products.dao.ProductRepo;
import org.example.web_mvc_products.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ProductRestApi {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/products")
    public List<Product> productList(){
        return productRepo.findAll();
    }
}

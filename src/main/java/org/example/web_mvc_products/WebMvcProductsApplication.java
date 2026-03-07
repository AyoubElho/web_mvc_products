package org.example.web_mvc_products;

import org.example.web_mvc_products.dao.ProductRepo;
import org.example.web_mvc_products.entity.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebMvcProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebMvcProductsApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(ProductRepo productRepo) {
        return args -> {
            Product product = Product
                    .builder()
                    .name("Laptop")
                    .price(500)
                    .quantity(20)
                    .build();
            productRepo.save(product);
            Product product1 = Product
                    .builder()
                    .name("SSD")
                    .price(200)
                    .quantity(20)
                    .build();
            productRepo.save(product1);
            Product product2 = Product
                    .builder()
                    .name("PlayStation 5")
                    .price(1110)
                    .quantity(20)
                    .build();
            productRepo.save(product2);

            productRepo.findAll().forEach(p -> {
                System.out.println(p.toString());
            });
        };
    }
}

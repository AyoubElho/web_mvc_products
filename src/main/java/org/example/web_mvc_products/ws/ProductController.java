package org.example.web_mvc_products.ws;

import jakarta.validation.Valid;
import org.example.web_mvc_products.dao.ProductRepo;
import org.example.web_mvc_products.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductRepo productRepo;

    @GetMapping("/findAll")
    public String findAll(Model model) {
        List<Product> products = productRepo.findAll();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String add(Model model) {
        model.addAttribute("product", new Product());
        return "form";
    }

    @PostMapping("/saveProduct")
    public String save(@Valid Product product, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            Product product1 = Product
                    .builder()
                    .name(product.getName())
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .build();
            productRepo.save(product1);
            return "redirect:/findAll";

        } else {
            return "form";
        }
    }

    @GetMapping("/deleteProduct")
    public String delete(@RequestParam(name = "id") Long id) {
        Product product = productRepo.findById(id).get();
        productRepo.delete(product);
        return "redirect:/findAll";
    }


    @GetMapping("/findAll/json")
    @ResponseBody
    public List<Product> findAlljson() {
        List<Product> products = productRepo.findAll();
        return products;
    }
}

package org.example.web_mvc_products.ws;

import org.example.web_mvc_products.dao.ProductRepo;
import org.example.web_mvc_products.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

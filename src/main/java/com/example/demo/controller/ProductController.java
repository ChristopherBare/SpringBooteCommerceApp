package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public List<Product> index() {
        return productService.findAll();
    }

    @GetMapping("/product/{id}")
    public String show(@PathVariable String id, Model model) {
        int productId = Integer.parseInt(id);
        Product product = productService.findById(productId);
        System.out.println(product);
        model.addAttribute(product);
        return "product";
    }

    @PostMapping("/product")
    public Product create(@RequestBody Map<String, ?> body) {
        return productService.save(newProductFromResponse(body));
    }

    @PostMapping("/product/{id}")
    public Product update(@PathVariable String id, @RequestBody Map<String, ?> body) {
        int productId = Integer.parseInt(id);

        //Get product
        Product product = productService.findById(productId);

        updateProductFromResponse(product, body);

        return productService.save(product);
    }

    @DeleteMapping("/product/{id}")
    public boolean delete(@PathVariable String id) {
        productService.deleteById(Integer.parseInt(id));
        return true;
    }

    private Product updateProductFromResponse(Product product, Map<String, ?> body) {
        double price = (Double) body.get("price");
        int quantity = (Integer) body.get("quantity");
        String description = (String) body.get("description");
        String name = (String) body.get("name");
        String brand = (String) body.get("brand");
        int rating = (Integer) body.get("rating");
        String image = (String) body.get("image");
        String category = (String) body.get("category");

        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setName(name);
        product.setBrand(brand);
        product.setRating(rating);
        product.setImage(image);
        product.setCategory(category);

        return product;
    }

    private  Product newProductFromResponse(Map<String, ?> body) {
        return updateProductFromResponse(new Product(), body);
    }


}

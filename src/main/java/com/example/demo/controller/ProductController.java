package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    // TODO: Either implement admin controls or remove these methods.
    
    @RequestMapping(value = "/product", method = {RequestMethod.POST, RequestMethod.PUT})
    public String createOrUpdate(@Valid Product product) {
        productService.save(product);
        return "redirect:/product/" + product.getId();
    }

    @DeleteMapping("/product/{id}")
    public boolean delete(@PathVariable long id) {
        productService.deleteById(id);
        return true;
    }
}

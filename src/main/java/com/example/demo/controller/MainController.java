package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.service.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.PostConstruct;
import java.util.List;

@Data
@Controller
@ControllerAdvice // This makes the `@ModelAttribute`s of this controller available to all controllers, for the navbar.
public class MainController {
    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @PostConstruct
    private void init() {
        Product iPhoneX = new Product(9999, 999.0, "64GB, iOS 11, space gray",
            "iPhone X", "Apple",
            "/image/1/iphonexfrontback-800x573.jpg", "Smart Phones");
        Product iPhone8 = new Product(9999, 799.0, "64GB, iOS 11, Silver",
            "iPhone 8", "Apple",
            "/image/2/iphone8-silver-select-2017.jpg", "Smart Phones");
        Product C7OLED = new Product(9999, 3000.00, "65\" Smart TV",
            "C7 OLED", "LG",
            "/image/3/C7_ST_Desktop_Front.jpg", "Televisions");
        Product MacbookPro = new Product(15000, 2800.00, "15\" laptop, 512GB SSD",
            "Macbook Pro", "Apple",
            "/image/4/apple_mlh32ll_a_15_4_macbook_pro_with_1293726.jpg", "Computers");
        productService.save(iPhoneX);
        productService.save(iPhone8);
        productService.save(C7OLED);
        productService.save(MacbookPro);
    }

    @ModelAttribute("products")
    public List<Product> products() {
        return productService.findAll();
    }

    @ModelAttribute("categories")
    public List<String> categories() {
        return productService.findDistinctCategories();
    }

    @ModelAttribute("brands")
    public List<String> brands() {
        return productService.findDistinctBrands();
    }

    @GetMapping("/filter")
    public String filter(@RequestParam(required = false) String category,
                         @RequestParam(required = false) String brand,
                         Model model) {
        List<Product> filtered = productService.findByBrandAndOrCategory(brand, category);
        model.addAttribute("filtered", filtered);
        return "filter";
    }
}

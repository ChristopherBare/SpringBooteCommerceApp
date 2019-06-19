package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.model.User;
import com.example.demo.services.interfaces.ProductService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Stream;

@Data
@Controller
public class MainController {
    ArrayList<User> users = new ArrayList<>();

    @Autowired
    ProductService productService;


    @GetMapping("/")
    public String main(Model model) {
        User admin = new User("admin@admin.com", "root", "admin", "admin");
        users.add(admin);

        model.addAttribute(users);

        return "main";
    }

    @ModelAttribute("products")
    public ArrayList<Product> products() {
        Product iPhoneX = new Product(999.00, 9999, "64GB, iOS 11, space gray",
                "iPhone X", "Apple", 5,
                "/image/1/iphonexfrontback-800x573.jpg", "Smart Phones");
        Product iPhone8 = new Product(799.00, 9999, "64GB, iOS 11, Silver",
                "iPhone 8", "Apple", 5,
                "/image/2/iphone8-silver-select-2017.jpg", "Smart Phones");
        Product C7OLED = new Product(3000.00, 9999, "65\" Smart TV",
                "C7 OLED", "LG", 5,
                "/image/3/C7_ST_Desktop_Front.jpg", "Televisions");
        Product MacbookPro = new Product(2800.00, 15000, "15\" laptop, 512GB SSD",
                "Macbook Pro", "Apple", 5,
                "/image/4/apple_mlh32ll_a_15_4_macbook_pro_with_1293726.jpg", "Computers");
        ArrayList<Product> products = new ArrayList<>();
        products.add(iPhoneX);
        products.add(iPhone8);
        products.add(C7OLED);
        products.add(MacbookPro);
        return products;
    }

    @ModelAttribute("categories")
    public Set<Category> categories() {
        LinkedHashSet<Category> c = new LinkedHashSet<>();
        c.add(new Category("All"));
        for(Product product : products())
            c.add(product.getCategory());
        return c;
    }

    @ModelAttribute("brands")
    public Set<String> brands() {
        LinkedHashSet<String> b = new LinkedHashSet<>();
        b.add("All");
        for (Product product : products())
            b.add(product.getBrand());
        return b;
    }

    @RequestMapping(value = "/sortByCategory/{category}", method = RequestMethod.GET)
    public Stream<Product> sortProductsByCategory(@PathVariable String category,
                                                  @ModelAttribute(name="products")
                                                 ArrayList<Product> products) {
        return products.stream().filter(x -> x.getCategory().toString().equals(category));
    }

    @RequestMapping(value = "/sortByBrand/{brand}", method = RequestMethod.GET)
    public Stream<Product> sortProductsByBrand(@PathVariable String brand,
                                               @ModelAttribute(name="products")
                                              ArrayList<Product> products) {
        return products.stream().filter(x -> x.getBrand().equals(brand));
    }
}

package com.example.demo.controller;

import com.example.demo.model.Product;
import com.example.demo.model.ProductRepository;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
@Controller
public class MainController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/")
    public String main(Model model) {
        //Product(double price, int quantity, String description, String name,
        //                   String brand, int rating, String image, String categoryName)
        ArrayList<Product> products = new ArrayList<>();
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

        products.add(iPhoneX);
        products.add(iPhone8);
        products.add(C7OLED);
        products.add(MacbookPro);


        model.addAttribute(products);

        return "main";
    }

    @GetMapping("/Product")
    public List<Product> index() {
        return productRepository.findAll();
    }

    @GetMapping("/Product/{id}")
    public Product show(@PathVariable String id) {
        int productId = Integer.parseInt(id);
        return productRepository.findById(productId).get();
    }

    @PostMapping("/Product")
    public Product create(@RequestBody Map<String, ?> body) {
        double price = (Double) body.get("price");
        int quantity = (Integer) body.get("quantity");
        String description = (String) body.get("description");
        String name = (String) body.get("name");
        String brand = (String) body.get("brand");
        int rating = (Integer) body.get("rating");
        String image = (String) body.get("image");
        String category = (String) body.get("category");

        return productRepository.save(new Product(price, quantity, description, name, brand, rating, image, category));
    }

    @PostMapping("/Product/{id}")
    public Product update(@PathVariable String id, @RequestBody Map<String, ?> body) {
        int productId = Integer.parseInt(id);

        //Get product
        Product product = productRepository.findById(productId).get();
        double price = (Double) body.get("price");
        int quantity = (Integer) body.get("quantity");
        String description = (String) body.get("description");
        String name = (String) body.get("name");
        String brand = (String) body.get("brand");
        int rating = (Integer) body.get("rating");
        String image = (String) body.get("image");

        product.setPrice(price);
        product.setQuantity(quantity);
        product.setDescription(description);
        product.setName(name);
        product.setBrand(brand);
        product.setRating(rating);
        product.setImage(image);

        return productRepository.save(product);
    }

    @DeleteMapping("/Product/{id}")
    public boolean delete(@PathVariable String id) {
        productRepository.delete(show(id));
        return true;
    }


}

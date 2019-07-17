package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public String show(@PathVariable int id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute(product);
        return "product";
    }
    
    // TODO: Are we using these?

    /*
    // Based on Twitter, this doesn't seem like the best way to do this.
    // You can have a form that makes a Product from its fields and posts it to the controller as a Product.
    @PostMapping("/product")
    public Product create(@RequestBody Map<String, ?> body) {
        return productService.save(newProductFromResponse(body));
    }
    
    // Similarly, update can probably be handled by putting the product to update in the model,
    // then making a form based on it.
    @PutMapping("/product/{id}")
    public Product update(@PathVariable String id, @RequestBody Map<String, ?> body) {
        int productId = Integer.parseInt(id);

        //Get product
        Product product = productService.findById(productId);

        updateProductFromResponse(product, body);

        return productService.save(product);
    }
    */
    
    /**
     * This can both create a new product and update an existing product.
     * It works by first sending the product to the form page through a get request,
     * then when the form posts or patches, it gets converted to a Product and this method is called.
     *
     * Patch is used because that represents updating an entity rather than creating or replacing like post and put.
     */
    @PostMapping("/product")
    @PatchMapping("/product")
    public void create(@Valid Product product, BindingResult br, Model model) {
        if(!br.hasErrors()) productService.save(product);
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

    private Product newProductFromResponse(Map<String, ?> body) {
        return updateProductFromResponse(new Product(), body);
    }
}

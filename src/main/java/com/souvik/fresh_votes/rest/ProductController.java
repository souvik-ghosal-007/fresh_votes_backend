package com.souvik.fresh_votes.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.souvik.fresh_votes.domain.Product;
import com.souvik.fresh_votes.domain.User;
import com.souvik.fresh_votes.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/products/new")
    public ResponseEntity<Product> createProduct(@RequestBody String request) throws JsonProcessingException {

        JsonObject json = new Gson().fromJson(request, JsonObject.class);

        User user = new ObjectMapper().readValue(json.get("user").toString(), User.class);

        Product product = new Product();
        product.setName(json.get("name").getAsString());
        product.setPublished(json.get("published").getAsBoolean());
        product.setUser(user);
        product.setDescription(json.get("description").getAsString());

        productService.save(product);

        return ResponseEntity.ok(product);
    }

    @GetMapping("/users/{user_id}/products")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable Long user_id) {
        final List<Product> products = productService.findProductsByUserId(user_id);
        return ResponseEntity.ok(products);
    }

    @GetMapping("/products/{product_id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long product_id) {
        final Product product = productService.findById(product_id);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/users/{user_id}/products/{product_id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long user_id, @PathVariable Long product_id, @RequestBody String request) {
        JsonObject json = new Gson().fromJson(request, JsonObject.class);

        Product product = productService.findById(product_id);

        if(product.getUser().getId() != user_id) return ResponseEntity.badRequest().build();

        if(json.has("name")) product.setName(json.get("name").getAsString());
        if(json.has("description")) product.setDescription(json.get("description").getAsString());
        if(json.has("published")) product.setPublished(json.get("published").getAsBoolean());

        productService.save(product);

        return ResponseEntity.ok(product);
    }

    @DeleteMapping ("/users/{user_id}/products/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long user_id, @PathVariable Long product_id) {
        Product product = productService.findById(product_id);

        if(product.getUser().getId() != user_id) return ResponseEntity.badRequest().build();

        productService.delete(product_id);

        return ResponseEntity.ok("The product has been deleted");
    }
}

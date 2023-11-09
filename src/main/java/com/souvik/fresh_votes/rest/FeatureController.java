package com.souvik.fresh_votes.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.souvik.fresh_votes.domain.Feature;
import com.souvik.fresh_votes.domain.Product;
import com.souvik.fresh_votes.service.FeatureService;
import com.souvik.fresh_votes.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products/{product_id}/features")
public class FeatureController {

    private final FeatureService featureService;
    private final ProductService productService;

    @Autowired
    public FeatureController(FeatureService featureService, ProductService productService) {
        this.featureService = featureService;
        this.productService = productService;
    }

    @GetMapping("")
    public ResponseEntity<List<Feature>> getAllFeatures(@PathVariable Long product_id) {
        List<Feature> features = featureService.findAll(product_id);

        return ResponseEntity.ok(features);
    }

    @PostMapping("")
    public ResponseEntity<Feature> createFeature(@PathVariable Long product_id, @RequestBody String request) {

        final JsonObject json = new Gson().fromJson(request, JsonObject.class);

        Product product = productService.findById(product_id);

        if(product == null) return ResponseEntity.notFound().build();

        final Feature feature = new Feature();
        if(json.has("title")) feature.setTitle(json.get("title").getAsString());
        if(json.has("description")) feature.setDescription(json.get("description").getAsString());
        if(json.has("status")) feature.setStatus(json.get("status").getAsString());
        feature.setProduct(product);

        featureService.save(feature);

        return ResponseEntity.ok(feature);
    }

    @GetMapping("/{feature_id}")
    public ResponseEntity<Feature> getFeature(@PathVariable Long product_id, @PathVariable Long feature_id) {
        final Feature feature = featureService.findById(product_id, feature_id);

        if(feature == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(feature);
    }

    @PutMapping("/{feature_id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable Long product_id, @PathVariable Long feature_id, @RequestBody String request) {
        final JsonObject json = new Gson().fromJson(request, JsonObject.class);

        final Feature feature = featureService.findById(product_id, feature_id);

        if(feature == null) return ResponseEntity.notFound().build();

        if(json.has("title")) feature.setTitle(json.get("title").getAsString());
        if(json.has("description")) feature.setDescription(json.get("description").getAsString());
        if(json.has("status")) feature.setStatus(json.get("status").getAsString());

        featureService.save(feature);

        return ResponseEntity.ok(feature);
    }

    @DeleteMapping("/{feature_id}")
    public ResponseEntity<String> deleteFeature(@PathVariable Long feature_id) {
        featureService.deleteById(feature_id);
        return ResponseEntity.ok("The feature has been deleted");
    }
}

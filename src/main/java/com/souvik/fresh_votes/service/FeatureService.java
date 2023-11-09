package com.souvik.fresh_votes.service;

import com.souvik.fresh_votes.domain.Feature;
import com.souvik.fresh_votes.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public List<Feature> findAll(Long id) {
        return featureRepository.findAllFeatureByProductId(id);
    }

    public Feature findById(Long productId, Long featureId) {
        return featureRepository.findFeatureByProductIdAndId(productId, featureId);
    }

    public void save(Feature feature) {
        featureRepository.save(feature);
    }

    public void deleteById(Long featureId) {
        featureRepository.deleteById(featureId);
    }
}

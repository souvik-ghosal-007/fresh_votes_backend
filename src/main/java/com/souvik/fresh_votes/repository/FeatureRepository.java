package com.souvik.fresh_votes.repository;

import com.souvik.fresh_votes.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findAllFeatureByProductId(Long id);
    Feature findFeatureByProductIdAndId(Long productId, Long featureId);
}

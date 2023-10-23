package com.example.pmaapi.product;

import com.example.pmaapi.category.Category;
import com.example.pmaapi.product.productImages.ProductImages;
import lombok.Builder;

import java.util.List;

@Builder
public record ProductDTO (
        Long id,
        String name,
        float price,
        String description,
        Gender gender,
        Category category,
        List<ProductImages>imageUrls
) { }

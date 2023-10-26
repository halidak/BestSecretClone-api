package com.example.pmaapi.product;

import com.example.pmaapi.category.Category;
import com.example.pmaapi.product.productImages.ProductImages;
import com.example.pmaapi.sizes.ProductClothingSizes;
import lombok.Builder;

import java.util.List;
import java.util.Set;

@Builder
public record ProductDTO (
        Long id,
        String name,
        float price,
        String description,
        Gender gender,
        Category category,
        List<ProductImages>imageUrls,
        Set<ProductClothingSizes> productSizes
) { }

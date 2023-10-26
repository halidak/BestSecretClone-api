package com.example.pmaapi.sizes;

import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.sizes.clothing.ClothingSize;
import com.example.pmaapi.sizes.clothing.ClothingSizesRepository;
import com.example.pmaapi.sizes.request.AddProductSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductClothingSizesService {

    private final ProductClothingSizesRepository productClothingSizesRepository;
    private final ProductRepository productRepository;
    private final ClothingSizesRepository clothingSizesRepository;

    public ProductClothingSizes addSize(AddProductSize request){
        Product product = productRepository.findById(request.getProductId()).orElse(null);
        ClothingSize clothingSize = clothingSizesRepository
                .findById(request.getClothingSizeId())
                .orElseThrow(() -> new RuntimeException("Clothing Size not found for ID: " + request.getClothingSizeId()));


        if (product != null && clothingSize != null) {
            ProductClothingSizes productClothingSizes = new ProductClothingSizes();
            productClothingSizes.setProduct(product);
            productClothingSizes.setClothingSize(clothingSize);
            productClothingSizes.setAmount(request.getAmount());

            ProductClothingSizes productSize = productClothingSizesRepository.save(productClothingSizes);
            return productSize;
        }

        return null;
    }

}

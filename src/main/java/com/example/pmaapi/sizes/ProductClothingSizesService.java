package com.example.pmaapi.sizes;

import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.sizes.clothing.ClothingSize;
import com.example.pmaapi.sizes.clothing.ClothingSizesRepository;
import com.example.pmaapi.sizes.request.AddProductSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

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

    //update amount
    public AddProductSize updateAmount(AddProductSize request){
        ProductClothingSizes productClothingSizes = productClothingSizesRepository
                .findByProduct_IdAndClothingSize_Id(request.getProductId(), request.getClothingSizeId());

        if(productClothingSizes != null){
            productClothingSizes.setAmount(request.getAmount());
            productClothingSizesRepository.save(productClothingSizes);

            AddProductSize updatedProductSize = new AddProductSize();
            updatedProductSize.setProductId(productClothingSizes.getProduct().getId());
            updatedProductSize.setClothingSizeId(productClothingSizes.getClothingSize().getId());
            updatedProductSize.setAmount(productClothingSizes.getAmount());

            return updatedProductSize;
        } else {
            throw new RuntimeException("Proizvod i/ili veličina odjeće nije pronađena.");
        }
    }
}

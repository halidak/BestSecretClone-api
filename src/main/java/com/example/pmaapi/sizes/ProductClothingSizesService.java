package com.example.pmaapi.sizes;

import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.sizes.clothing.ClothingSize;
import com.example.pmaapi.sizes.clothing.ClothingSizesRepository;
import com.example.pmaapi.sizes.request.AddProductSize;
import com.example.pmaapi.sizes.response.ProductSizeDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public AddProductSize updateAmount(AddProductSize request) {
        // Fetch the ProductClothingSizes entity based on product and clothing size IDs
        ProductClothingSizes productClothingSizes = productClothingSizesRepository
                .findByProduct_IdAndClothingSize_Id(request.getProductId(), request.getClothingSizeId());

        // Check if the entity was found
        if (productClothingSizes == null) {
            throw new RuntimeException("Product Size not found for Product ID: " + request.getProductId() + " and Clothing Size ID: " + request.getClothingSizeId() + "");
        }

        // Update the amount and save the entity
        productClothingSizes.setAmount(request.getAmount());
        productClothingSizesRepository.save(productClothingSizes);

        // Prepare the response object
        AddProductSize updatedProductSize = AddProductSize.builder()
                .productId(productClothingSizes.getProduct().getId())
                .clothingSizeId(productClothingSizes.getClothingSize().getId())
                .amount(productClothingSizes.getAmount())
                .build();

        return updatedProductSize;
    }

    public List<ProductSizeDTO> findByProduct_Id(Long productId) {
        Set<ProductClothingSizes> productClothingSizesSet = productClothingSizesRepository.findByProduct_Id(productId);

        return productClothingSizesSet.stream()
                .map(productClothingSizes -> ProductSizeDTO.builder()
                        .id(productClothingSizes.getId())
                        .clothingSizeId(productClothingSizes.getClothingSize().getId())
                        .clothingSizeName(productClothingSizes.getClothingSize().getName())
                        .amount(productClothingSizes.getAmount())
                        .build())
                .collect(Collectors.toList());
    }

}

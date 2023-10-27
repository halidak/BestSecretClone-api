package com.example.pmaapi.sizes.clothing;

import com.example.pmaapi.product.Product;
import com.example.pmaapi.product.ProductRepository;
import com.example.pmaapi.sizes.ProductClothingSizes;
import com.example.pmaapi.sizes.clothing.request.AddSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClothingSizesService {

    private final ClothingSizesRepository clothingSizesRepository;
    private final ProductRepository productRepository;

    public ClothingSize addSize(AddSize request){
        ClothingSize clothingSizes = ClothingSize.builder()
                .name(request.getName())
                .build();

        return clothingSizesRepository.save(clothingSizes);
    }

    public List<ClothingSize> getAll(){
        return clothingSizesRepository.findAll();
    }

    public List<ClothingSize> getAvailableClothingSizesForProduct(Long productId) {
        List<ClothingSize > availableClothingSizes = clothingSizesRepository.findDistinctAvailableClothingSizesForProduct(productId);
        return  availableClothingSizes;
    }

}

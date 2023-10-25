package com.example.pmaapi.sizes.clothing;

import com.example.pmaapi.sizes.clothing.request.AddSize;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClothingSizesService {

    private final ClothingSizesRepository clothingSizesRepository;

    public ClothingSize addSize(AddSize request){
        ClothingSize clothingSizes = ClothingSize.builder()
                .name(request.getName())
                .build();

        return clothingSizesRepository.save(clothingSizes);
    }

    public List<ClothingSize> getAll(){
        return clothingSizesRepository.findAll();
    }
}

package com.example.pmaapi.sizes;

import com.example.pmaapi.sizes.request.AddProductSize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/csize-product")
@RequiredArgsConstructor
public class ProductClothingSizesController {

    private final ProductClothingSizesService productClothingSizesService;

    @PostMapping("/add")
    public ResponseEntity<ProductClothingSizes> add(@RequestBody AddProductSize request){
        return ResponseEntity.ok(productClothingSizesService.addSize(request));
    }

    //update amount
    @PutMapping("/update-amount")
    public ResponseEntity<AddProductSize> updateAmount(@RequestBody AddProductSize request){
        return ResponseEntity.ok(productClothingSizesService.updateAmount(request));
    }
}

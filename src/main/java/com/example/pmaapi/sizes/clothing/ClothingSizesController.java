package com.example.pmaapi.sizes.clothing;

import com.example.pmaapi.sizes.clothing.request.AddSize;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/csize")
@RequiredArgsConstructor
public class ClothingSizesController {

    private final ClothingSizesService clothingSizesService;

    @PostMapping("/add")
    public ResponseEntity<ClothingSize> addSize(@RequestBody AddSize request){
        return ResponseEntity.ok(clothingSizesService.addSize(request));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ClothingSize>> getAll(){
        return ResponseEntity.ok(clothingSizesService.getAll());
    }
}

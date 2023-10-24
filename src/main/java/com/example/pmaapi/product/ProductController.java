package com.example.pmaapi.product;

import com.example.pmaapi.product.request.AddImages;
import com.example.pmaapi.product.request.AddProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody AddProduct productRequest){
        ProductDTO productDTO = productService.addProduct(productRequest);
        return ResponseEntity.ok(productDTO);
    }

    @GetMapping("/get-by-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getProductsByCategoryAndGender(@PathVariable Long categoryId){
        return  ResponseEntity.ok(productService.getProductsByCategory(categoryId));
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTO>> getAll(
    ){
        return  ResponseEntity.ok(productService.getAll());
    }
}

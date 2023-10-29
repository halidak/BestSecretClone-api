package com.example.pmaapi.product;

import com.example.pmaapi.addressData.AddressData;
import com.example.pmaapi.addressData.request.UpdateAddress;
import com.example.pmaapi.product.request.AddImages;
import com.example.pmaapi.product.request.AddProduct;
import com.example.pmaapi.product.request.UpdateProduct;
import com.example.pmaapi.user.request.UpdateUser;
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
    public ResponseEntity<List<ProductDTO>> getAll(){
        return  ResponseEntity.ok(productService.getAll());
    }

    @GetMapping("/get-by-gender-and-category/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getByCategoryAndGender(@PathVariable Long categoryId, @RequestParam Gender gender){
        return  ResponseEntity.ok(productService.getByCategoryAndGender(categoryId, gender));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Artikal sa ID \" + productId + \" je uspešno obrisan.");
    }

    @PutMapping("update/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long productId, @RequestBody UpdateProduct request){
        ProductDTO productDTO = productService.updateProduct(productId, request);

        if(productDTO != null){
            return ResponseEntity.ok(productDTO);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/get-by-id/{productId}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long productId){
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @GetMapping("get-available/{categoryId}")
    public ResponseEntity<List<ProductDTO>> getAvailable(@PathVariable Long categoryId, @RequestParam Gender gender){
        return ResponseEntity.ok(productService.getByCategoryGenderAndClothingSize(categoryId, gender));
    }

    @GetMapping("/user-fav/{userId}")
    public ResponseEntity<List<ProductDTO>> getUserFav(@PathVariable Long userId){
        return  ResponseEntity.ok(productService.getUserFavourites(userId));
    }


}

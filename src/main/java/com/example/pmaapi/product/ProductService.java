package com.example.pmaapi.product;

import com.example.pmaapi.addressData.AddressData;
import com.example.pmaapi.addressData.request.UpdateAddress;
import com.example.pmaapi.category.Category;
import com.example.pmaapi.category.CategoryRepository;
import com.example.pmaapi.product.productImages.ProductImages;
import com.example.pmaapi.product.productImages.ProductImagesRepository;
import com.example.pmaapi.product.request.AddImages;
import com.example.pmaapi.product.request.AddProduct;
import com.example.pmaapi.product.request.UpdateProduct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductImagesRepository productImagesRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO addProduct(AddProduct request){
        Category category = categoryRepository.findById(request.getCategoryId()).orElse(null);

        Product product = Product.builder()
                .price(request.getPrice())
                .name(request.getName())
                .description(request.getDescription())
                .gender(request.getGender())
                .category(category)
                .build();

        productRepository.save(product);

        for (String imageUrl : request.getImageUrls()) {
            ProductImages image = new ProductImages();
            image.setPhoto(imageUrl);
            image.setProduct(product);
            productImagesRepository.save(image);
        }

        ProductDTOMapper mapper = new ProductDTOMapper();
        ProductDTO productDTO = mapper.apply(product);


        return productDTO;
    }

    public List<ProductDTO> getProductsByCategory(Long categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId);
        ProductDTOMapper mapper = new ProductDTOMapper();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> mapper.apply(product))
                .collect(Collectors.toList());

        return productDTOs;
    }

    public List<ProductDTO> getAll() {
        List<Product> products = productRepository.findAll();
        ProductDTOMapper productDTOMapper = new ProductDTOMapper();
        return products.stream()
                .map(product -> productDTOMapper.apply(product))
                .collect(Collectors.toList());
    }

    //by category and gender
    public List<ProductDTO> getByCategoryAndGender(Long categoryId, Gender gender){
        List<Product> products = productRepository.findByCategoryIdAndGender(categoryId, gender);
        ProductDTOMapper mapper = new ProductDTOMapper();
        List<ProductDTO> productDTOs = products.stream()
                .map(product -> mapper.apply(product))
                .collect(Collectors.toList());

        return productDTOs;
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    public ProductDTO updateProduct(Long productId, UpdateProduct request){
        Product product = productRepository.findById(productId).orElse(null);

        if(product != null) {
            product.setName(request.getName());
            product.setDescription(request.getDescription());
            product.setPrice(request.getPrice());
            product.setGender(request.getGender());

            ProductDTOMapper mapper = new ProductDTOMapper();
            ProductDTO productDTO = mapper.apply(product);

            productRepository.save(product);
            return productDTO;
        }

        return  null;
    }

    public ProductDTO getProductById(Long productId){
        Product product = productRepository.findById(productId).orElse(null);

        ProductDTOMapper mapper = new ProductDTOMapper();
        ProductDTO productDTO = mapper.apply(product);

        return productDTO;
    }
}
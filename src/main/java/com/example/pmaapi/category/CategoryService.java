package com.example.pmaapi.category;

import com.example.pmaapi.category.request.AddCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public Category addCategory(AddCategory request){
        Category category = Category.builder()
                .name(request.getName())
                .build();

        return categoryRepository.save(category);
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }
}

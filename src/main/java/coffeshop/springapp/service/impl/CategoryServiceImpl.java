package coffeshop.springapp.service.impl;

import coffeshop.springapp.model.entity.Category;
import coffeshop.springapp.model.enums.CategoryEnum;
import coffeshop.springapp.repository.CategoryRepository;
import coffeshop.springapp.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void initCategory() {

        if(categoryRepository.count() == 0) {

            Arrays.stream(CategoryEnum.values())
                    .forEach(categoryEnum -> {
                        Category category = new Category();
                        category.setName(categoryEnum);
                        switch (categoryEnum) {
                            case Drink -> category.setNeededTime(1);
                            case Coffee -> category.setNeededTime(2);
                            case Other -> category.setNeededTime(5);
                            case Cake -> category.setNeededTime(10);
                        }
                        categoryRepository.save(category);
                    });
        }
    }

    @Override
    public Category findByCategoryName(CategoryEnum category) {

        return categoryRepository.findByName(category).orElse(null);
    }
}

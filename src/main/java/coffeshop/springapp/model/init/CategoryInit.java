package coffeshop.springapp.model.init;

import coffeshop.springapp.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CategoryInit implements CommandLineRunner {
    private final CategoryService categoryService;

    public CategoryInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategory();

    }
}

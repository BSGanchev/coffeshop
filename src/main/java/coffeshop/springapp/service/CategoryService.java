package coffeshop.springapp.service;

import coffeshop.springapp.model.entity.Category;
import coffeshop.springapp.model.enums.CategoryEnum;

public interface CategoryService {

    void initCategory();

    Category findByCategoryName(CategoryEnum category);
}

package coffeshop.springapp.repository;

import coffeshop.springapp.model.entity.Category;
import coffeshop.springapp.model.enums.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

    Optional<Category> findByName(CategoryEnum categoryName);
}

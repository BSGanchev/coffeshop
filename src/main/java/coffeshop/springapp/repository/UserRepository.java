package coffeshop.springapp.repository;

import coffeshop.springapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    @Query("SELECT u FROM User u ORDER BY size(u.orders) DESC")
    List<User> findAllByOrdersCountDes();
}

package coffeshop.springapp.repository;

import coffeshop.springapp.model.entity.Order;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findAllByOrderByPriceDesc();

    void deleteById(@NotNull UUID id);
}

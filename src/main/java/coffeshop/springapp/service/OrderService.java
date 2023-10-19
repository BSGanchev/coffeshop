package coffeshop.springapp.service;

import coffeshop.springapp.model.dto.OrderAddDTO;
import coffeshop.springapp.model.dto.OrderViewDTO;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    void addOrder(OrderAddDTO orderAddDTO);

    List<OrderViewDTO> findAllByOrderByPriceDesc();

    void readyOrder(UUID id);
}

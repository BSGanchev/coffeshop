package coffeshop.springapp.service.impl;

import coffeshop.springapp.model.dto.OrderAddDTO;
import coffeshop.springapp.model.dto.OrderViewDTO;
import coffeshop.springapp.model.entity.Order;
import coffeshop.springapp.repository.OrderRepository;
import coffeshop.springapp.service.CategoryService;
import coffeshop.springapp.service.OrderService;
import coffeshop.springapp.service.UserService;
import coffeshop.springapp.util.CurrentUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CurrentUser currentUser;
    private final CategoryService categoryService;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            CurrentUser currentUser,
                            CategoryService categoryService,  UserService userService) {

        this.orderRepository = orderRepository;
        this.currentUser = currentUser;
        this.categoryService = categoryService;
        this.userService = userService;
    }
    @Override
    public void addOrder(OrderAddDTO orderAddDTO) {
        orderRepository.save(map(orderAddDTO));
    }

    @Override
    public List<OrderViewDTO> findAllByOrderByPriceDesc() {

        List<Order> allOrdersOrderByPriceDesc = orderRepository.findAllByOrderByPriceDesc();
        return allOrdersOrderByPriceDesc.stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    public void readyOrder(UUID id) {

        orderRepository.deleteById(id);
    }

    private Order map(OrderAddDTO orderAddDTO) {

        Order order = new Order();
        order.setCategory(categoryService.findByCategoryName(orderAddDTO.getCategory()));
        order.setEmployee(userService.findById(currentUser.getId()));
        order.setOrderTime(orderAddDTO.getTime());
        order.setPrice(orderAddDTO.getPrice());
        order.setName(orderAddDTO.getName());
        order.setDescription(orderAddDTO.getDescription());

        return order;
    }
    private OrderViewDTO map(Order order) {
        OrderViewDTO orderViewDTO = new OrderViewDTO();
        orderViewDTO.setId(order.getId());
        orderViewDTO.setName(order.getName());
        orderViewDTO.setCategory(order.getCategory());

        return orderViewDTO;
    }
}

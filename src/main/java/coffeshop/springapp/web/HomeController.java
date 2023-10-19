package coffeshop.springapp.web;

import coffeshop.springapp.model.dto.OrderViewDTO;
import coffeshop.springapp.service.OrderService;
import coffeshop.springapp.service.UserService;
import coffeshop.springapp.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {
    private final CurrentUser currentUser;
    private final OrderService orderService;
    private final UserService userService;

    public HomeController(CurrentUser currentUser, OrderService orderService, UserService userService) {
        this.currentUser = currentUser;
        this.orderService = orderService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model) {
        if (!currentUser.isLogged()) {
            return "index";
        }
        List<OrderViewDTO> allOrders = orderService.findAllByOrderByPriceDesc();

        int totalTime = allOrders.stream().map(orderViewDTO -> orderViewDTO.getCategory().getNeededTime())
                .mapToInt(Integer::valueOf).sum();

        model.addAttribute("orders", allOrders);
        model.addAttribute("totalTime", totalTime);

        model.addAttribute("users", userService.findAllUsersAndCountOfOrders());

        return "home";
    }
}

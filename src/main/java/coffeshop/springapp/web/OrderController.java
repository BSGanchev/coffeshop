package coffeshop.springapp.web;

import coffeshop.springapp.model.dto.OrderAddDTO;
import coffeshop.springapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/order-add")
    public String addOrder(){
        return "order-add";
    }
    @PostMapping("/order-add")
    public String addOrderConfirm(@Valid OrderAddDTO orderAddDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            redirectAttributes.addFlashAttribute("orderAddDTO", orderAddDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.orderAddDTO", bindingResult);

            return "redirect:order-add";
        }
        orderService.addOrder(orderAddDTO);

        return "redirect:/";
    }

    @GetMapping("/ready/{id}")
    public String ready(@PathVariable UUID id){

        orderService.readyOrder(id);

        return "redirect:/";
    }

    @ModelAttribute
    public OrderAddDTO orderAddDTO(){
        return new OrderAddDTO();
    }
}

package coffeshop.springapp.web;

import coffeshop.springapp.model.dto.UserLoginDTO;
import coffeshop.springapp.model.dto.UserRegistrationDTO;
import coffeshop.springapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerConfirm(@Valid UserRegistrationDTO userRegistrationDTO,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors() || !userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {

            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);
            return "redirect:register";
        }

        userService.registerUser(userRegistrationDTO);

        return "redirect:login";
    }

    @GetMapping("/login")
    public String login() {

        return "login";
    }

    @PostMapping("/login")
    public String loginConfirm(@Valid UserLoginDTO userLoginDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:login";
        }
        String username = userLoginDTO.getUsername();
        String password = userLoginDTO.getPassword();

        if (!userService.loginSuccess(username, password)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("userNotFound", true);

            return "redirect:login";
        }

        userService.loginUser(userLoginDTO);

        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(){
        userService.logoutUser();
        return "redirect:/";
    }

    @ModelAttribute
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }
}

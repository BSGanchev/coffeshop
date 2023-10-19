package coffeshop.springapp.service.impl;

import coffeshop.springapp.model.dto.UserLoginDTO;
import coffeshop.springapp.model.dto.UserRegistrationDTO;
import coffeshop.springapp.model.dto.UserViewDTO;
import coffeshop.springapp.model.entity.User;
import coffeshop.springapp.repository.UserRepository;
import coffeshop.springapp.service.UserService;
import coffeshop.springapp.util.CurrentUser;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserRegistrationDTO userRegistrationDTO) {
        userRepository.save(map(userRegistrationDTO));
    }

    @Override
    public void loginUser(UserLoginDTO userLoginDTO) {
        currentUser.setLogged(true);
        currentUser.setId(Objects.requireNonNull(userRepository.findByUsername(userLoginDTO.getUsername()).orElse(null)).getId());
        currentUser.setUsername(userLoginDTO.getUsername());
    }

    @Override
    public void logoutUser() {
        currentUser.setId(null);
        currentUser.setUsername(null);
        currentUser.setLogged(false);
    }

    @Override
    public boolean loginSuccess(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);

        return user != null && passwordEncoder.matches(password, user.getPassword());
    }

    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<UserViewDTO> findAllUsersAndCountOfOrders() {
        return userRepository.findAllByOrdersCountDes()
                .stream()
                .map(user -> {
                    UserViewDTO userViewDTO = new UserViewDTO();
                    userViewDTO.setUsername(user.getUsername());
                    userViewDTO.setCountOfOrder(user.getOrders().size());

                    return userViewDTO;
                }).collect(Collectors.toList());
    }


    private User map(UserRegistrationDTO userRegistrationDTO) {

        User user = new User();
        user.setUsername(userRegistrationDTO.getUsername());
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));
        return user;
    }

}

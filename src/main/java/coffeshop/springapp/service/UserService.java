package coffeshop.springapp.service;

import coffeshop.springapp.model.dto.UserLoginDTO;
import coffeshop.springapp.model.dto.UserRegistrationDTO;
import coffeshop.springapp.model.dto.UserViewDTO;
import coffeshop.springapp.model.entity.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    void registerUser(UserRegistrationDTO userRegistrationDTO);

    void loginUser(UserLoginDTO userLoginDTO);

    void logoutUser();
    boolean loginSuccess(String username, String password);

    User findById(UUID id);

    List<UserViewDTO> findAllUsersAndCountOfOrders();
}

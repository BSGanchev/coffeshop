package coffeshop.springapp.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class UserLoginDTO {

    private UUID id;
    @NotBlank
    @Size(min = 5, max = 20, message = "Username length must be between 5 and 20 characters.")
    private String username;
    @NotBlank
    @Size(min = 3, message = "Password length must be more than 3 characters.")
    private String password;

    public UserLoginDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

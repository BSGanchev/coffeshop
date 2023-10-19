package coffeshop.springapp.model.dto;

public class UserViewDTO {
    private String username;
    private Integer countOfOrder;

    public UserViewDTO() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getCountOfOrder() {
        return countOfOrder;
    }

    public void setCountOfOrder(Integer countOfOrder) {
        this.countOfOrder = countOfOrder;
    }
}

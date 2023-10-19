package coffeshop.springapp.model.dto;

import coffeshop.springapp.model.entity.Category;
import coffeshop.springapp.model.entity.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public class OrderViewDTO {
    private UUID id;
    private String name;
    private BigDecimal price;
    private Category category;
    private List<User> employees;

    public OrderViewDTO() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}

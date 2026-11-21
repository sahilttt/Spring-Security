package com.securityexample.security.dto;

import com.securityexample.security.entity.User;

import java.util.ArrayList;
import java.util.List;

public class UserResponseDTO {

    private Long id;
    private String name;
    private String email;
    private List<OrderResponseDTO> orders = new ArrayList<>();

    public UserResponseDTO() {
    }

    public UserResponseDTO(Long id, String name, String email, List<OrderResponseDTO> orders) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.orders = orders != null ? orders : new ArrayList<>();
    }

    public static UserResponseDTO fromEntity(User user) {
        if (user == null) return null;
        List<OrderResponseDTO> orderDTOs = user.getOrders() != null
                ? user.getOrders().stream().map(OrderResponseDTO::fromEntity).toList()
                : new ArrayList<>();
        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), orderDTOs);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<OrderResponseDTO> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderResponseDTO> orders) {
        this.orders = orders;
    }
}

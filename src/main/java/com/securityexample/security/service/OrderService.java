package com.securityexample.security.service;

import com.securityexample.security.dto.OrderRequestDTO;
import com.securityexample.security.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO createOrder(Long userId, OrderRequestDTO request);

    List<OrderResponseDTO> getOrdersByUserId(Long userId);

    List<OrderResponseDTO> getAllOrders();

    OrderResponseDTO getOrderById(Long id);

    OrderResponseDTO updateOrder(Long id, OrderRequestDTO request);

    void deleteOrder(Long id);
}

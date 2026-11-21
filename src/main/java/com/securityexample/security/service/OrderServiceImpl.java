package com.securityexample.security.service;

import com.securityexample.security.dto.OrderRequestDTO;
import com.securityexample.security.dto.OrderResponseDTO;
import com.securityexample.security.entity.Order;
import com.securityexample.security.entity.OrderStatus;
import com.securityexample.security.entity.User;
import com.securityexample.security.exception.ResourceNotFoundException;
import com.securityexample.security.repository.OrderRepository;
import com.securityexample.security.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public OrderResponseDTO createOrder(Long userId, OrderRequestDTO request) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        Order order = new Order(
                request.getProductName().trim(),
                request.getQuantity(),
                request.getTotalAmount(),
                request.getStatus() != null ? request.getStatus() : OrderStatus.PLACED
        );
        user.addOrder(order);

        return OrderResponseDTO.fromEntity(orderRepository.save(order));
    }

    @Override
    public List<OrderResponseDTO> getOrdersByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new ResourceNotFoundException("User not found with id: " + userId);
        }
        return orderRepository.findByUserId(userId).stream()
                .map(OrderResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(OrderResponseDTO::fromEntity)
                .toList();
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return OrderResponseDTO.fromEntity(order);
    }

    @Override
    @Transactional
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

        order.setProductName(request.getProductName().trim());
        order.setQuantity(request.getQuantity());
        order.setTotalAmount(request.getTotalAmount());
        if (request.getStatus() != null) {
            order.setStatus(request.getStatus());
        }

        return OrderResponseDTO.fromEntity(orderRepository.save(order));
    }

    @Override
    @Transactional
    public void deleteOrder(Long id) {
        if (!orderRepository.existsById(id)) {
            throw new ResourceNotFoundException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }
}

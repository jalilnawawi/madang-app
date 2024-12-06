package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.orderDto.request.CreateOrderRequestDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.CreateOrderResponseDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.GetAllOrderResponseDto;
import com.jalil_be_app.madang_app.dto.orderDto.response.GetOrderResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.response.GetAllProductResponseDto;
import com.jalil_be_app.madang_app.dto.productDto.response.GetProductResponseDto;
import com.jalil_be_app.madang_app.model.entity.Order;
import com.jalil_be_app.madang_app.model.entity.Restaurant;
import com.jalil_be_app.madang_app.model.entity.account.Role;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.entity.account.UserRole;
import com.jalil_be_app.madang_app.repository.OrderRepository;
import com.jalil_be_app.madang_app.repository.RestaurantRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.OrderService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public CreateOrderResponseDto createOrder(String token, CreateOrderRequestDto createOrderRequestDto) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);

        User existingUser = userRepository.findById(userIdFromToken).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

        Restaurant existingRestaurant = restaurantRepository.findById(UUID.fromString(createOrderRequestDto.getRestaurantId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Restaurant not found")
        );

        Order order = new Order();
        order.setUser(existingUser);
        order.setRestaurant(existingRestaurant);
        order.setCompleted(false);
        orderRepository.save(order);

        CreateOrderResponseDto responseDto = new CreateOrderResponseDto();
        responseDto.setOrderId(order.getId());
        responseDto.setUserId(existingUser.getId());
        responseDto.setUsername(existingUser.getUsername());
        responseDto.setRestaurantId(existingRestaurant.getId());
        responseDto.setRestaurantName(existingRestaurant.getName());
        responseDto.setCompleted(order.isCompleted());

        return responseDto;
    }

    @Override
    public void deleteOrder(String token, UUID orderId) {
        UUID userIdFromToken = jwtService.getUserIdfromToken(token);
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found")
        );
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<GetAllOrderResponseDto> getAll() {
        List<Order> orderList = orderRepository.findAll();
        return orderList.stream().map(
                order -> new GetAllOrderResponseDto(
                        order.getId(),
                        order.getUser().getUsername(),
                        order.getRestaurant().getName(),
                        order.getTotalPrice(),
                        order.isCompleted()
                )
        ).collect(Collectors.toList());
    }

    @Override
    public GetOrderResponseDto getOrderById(UUID orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found")
        );

        GetOrderResponseDto responseDto = new GetOrderResponseDto();
        responseDto.setOrderId(order.getId());
        responseDto.setUsername(order.getUser().getUsername());
        responseDto.setRestaurantName(order.getRestaurant().getName());
        responseDto.setTotalPrice(order.getTotalPrice());
        responseDto.setCompleted(order.isCompleted());
        return responseDto;
    }
}

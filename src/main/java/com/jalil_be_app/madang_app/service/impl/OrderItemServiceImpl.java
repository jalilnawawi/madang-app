package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.orderDetailDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.request.UpdateQtyOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.response.CreateOrderItemResponseDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.response.UpdateQtyOrderItemResponseDto;
import com.jalil_be_app.madang_app.model.entity.Order;
import com.jalil_be_app.madang_app.model.entity.OrderItem;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.repository.OrderItemRepository;
import com.jalil_be_app.madang_app.repository.OrderRepository;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.service.OrderItemService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    JwtService jwtService;

    @Transactional
    @Override
    public CreateOrderItemResponseDto createOrderItem(String token, CreateOrderItemRequestDto createOrderItemRequestDto) {
        UUID userId = jwtService.getUserIdfromToken(token);
        Order existingOrder = orderRepository.findByUserId(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found")
        );

        if (existingOrder.isCompleted()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Please create a new order, last order has been closed");
        }

        Product existingProduct = productRepository.findById(UUID.fromString(createOrderItemRequestDto.getProductId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
                );

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(existingOrder);

        if (existingOrder.getRestaurant().getId() == existingProduct.getRestaurant().getId()) {
            orderItem.setProduct(existingProduct);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }
        orderItem.setQuantity(createOrderItemRequestDto.getQuantity());

        orderItem.setPrice(existingProduct.getPrice());

        orderItemRepository.save(orderItem);

        Double totalPrice = orderItem.getProduct().getPrice() * orderItem.getQuantity();

        CreateOrderItemResponseDto responseDto = new CreateOrderItemResponseDto();
        responseDto.setOrderId(existingOrder.getId());
        responseDto.setOrderItemId(orderItem.getId());
        responseDto.setProductName(existingProduct.getName());
        responseDto.setPrice(existingProduct.getPrice());
        responseDto.setQuantity(orderItem.getQuantity());
        responseDto.setTotalPrice(totalPrice);
        return responseDto;
    }

    @Override
    public UpdateQtyOrderItemResponseDto updateQtyOrderItem(UUID orderItemId, UpdateQtyOrderItemRequestDto updateQtyOrderItemRequestDto) {
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "OrderItem not found")
        );

        if (!orderItem.getOrder().isCompleted()){
            orderItem.setQuantity(updateQtyOrderItemRequestDto.getQuantity());
            orderItem.setPrice(
                    orderItem.getProduct().getPrice() * updateQtyOrderItemRequestDto.getQuantity()
            );
            orderItemRepository.save(orderItem);

            UpdateQtyOrderItemResponseDto responseDto = new UpdateQtyOrderItemResponseDto();
            responseDto.setOrderId(orderItem.getOrder().getId());
            responseDto.setOrderItemId(orderItem.getId());
            responseDto.setProductName(orderItem.getProduct().getName());
            responseDto.setPrice(orderItem.getProduct().getPrice());
            responseDto.setQuantity(orderItem.getQuantity());
            responseDto.setTotalPrice(orderItem.getPrice());
            return responseDto;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order has closed, please create new order!");
        }


    }

    @Override
    public void deleteOrderItem(UUID orderItemId) {
        orderItemRepository.deleteById(orderItemId);
    }

    @Override
    public List<OrderItem> getAllbyOrderId(UUID orderId) {
        return orderItemRepository.findByOrderId(orderId);
    }
}

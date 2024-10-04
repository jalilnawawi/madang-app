package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.orderDetailDto.request.CreateOrderItemRequestDto;
import com.jalil_be_app.madang_app.dto.orderDetailDto.response.CreateOrderItemResponseDto;
import com.jalil_be_app.madang_app.model.entity.Order;
import com.jalil_be_app.madang_app.model.entity.OrderItem;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.repository.OrderItemRepository;
import com.jalil_be_app.madang_app.repository.OrderRepository;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.service.OrderItemService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Transactional
    @Override
    public CreateOrderItemResponseDto createOrderItem(CreateOrderItemRequestDto createOrderItemRequestDto) {
        Order existingOrder = orderRepository.findById(UUID.fromString(createOrderItemRequestDto.getOrderId()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found")
        );

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

        Double totalPrice = existingProduct.getPrice() * createOrderItemRequestDto.getQuantity();
        orderItem.setTotalPrice(totalPrice);

        orderItemRepository.save(orderItem);

        CreateOrderItemResponseDto responseDto = new CreateOrderItemResponseDto();
        responseDto.setOrderId(existingOrder.getId());
        responseDto.setOrderItemId(orderItem.getId());
        responseDto.setProductName(existingProduct.getName());
        responseDto.setPrice(existingProduct.getPrice());
        responseDto.setQuantity(orderItem.getQuantity());
        responseDto.setTotalPrice(orderItem.getTotalPrice());
        return responseDto;
    }
}

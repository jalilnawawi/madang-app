package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.orderFacadeDto.request.ConfirmOrderRequestDto;
import com.jalil_be_app.madang_app.dto.orderFacadeDto.response.ConfirmOrderResponseDto;
import com.jalil_be_app.madang_app.model.entity.Order;
import com.jalil_be_app.madang_app.model.entity.OrderItem;
import com.jalil_be_app.madang_app.model.enums.PaymentMethod;
import com.jalil_be_app.madang_app.repository.OrderItemRepository;
import com.jalil_be_app.madang_app.repository.OrderRepository;
import com.jalil_be_app.madang_app.service.OrderFacade;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import jakarta.transaction.Transactional;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class OrderFacadeImpl implements OrderFacade {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    JwtService jwtService;

    @Transactional
    @Override
    public ConfirmOrderResponseDto confirm(String token, ConfirmOrderRequestDto confirmOrderRequestDto) {
        UUID userId = jwtService.getUserIdfromToken(token);

        Order existingOrder = orderRepository.findByUserId(userId).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Order not found")
        );

        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(existingOrder.getId());

        double grandTotal = 0;
        for (OrderItem orderItem : orderItemList){
            double totalPrice = orderItem.getProduct().getPrice() * orderItem.getQuantity();
            grandTotal += totalPrice;
            existingOrder.setTotalPrice(grandTotal);
        }
        existingOrder.setCompleted(true);
        orderRepository.save(existingOrder);

        ConfirmOrderResponseDto responseDto = new ConfirmOrderResponseDto();
        responseDto.setRestaurantName(existingOrder.getRestaurant().getName());
        responseDto.setCustomerName(existingOrder.getUser().getFullname());
        responseDto.setTotalPrice(grandTotal);

        if (confirmOrderRequestDto.getPaymentMethod().equalsIgnoreCase("shopeepay")){
            responseDto.setPaymentMethod(PaymentMethod.SHOPEEPAY);
        } else if (confirmOrderRequestDto.getPaymentMethod().equalsIgnoreCase("mbanking") ||
                confirmOrderRequestDto.getPaymentMethod().equalsIgnoreCase("transfer bank")
        ){
            responseDto.setPaymentMethod(PaymentMethod.TRANSFER_BANK);
        } else {
            responseDto.setPaymentMethod(PaymentMethod.CASH);
        }
        responseDto.setCompleted(existingOrder.isCompleted());

        return responseDto;

    }
}

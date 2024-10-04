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
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderFacadeImpl implements OrderFacade {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    JwtService jwtService;

    @Override
    public ConfirmOrderResponseDto confirm(String token, ConfirmOrderRequestDto confirmOrderRequestDto) {
        UUID userId = jwtService.getUserIdfromToken(token);

        Order order = orderRepository.findByUserId(userId);
        List<OrderItem> orderItemList = orderItemRepository.findByOrderId(order.getId());

        double grandTotal = 0;
        for (OrderItem orderItem : orderItemList){
            double totalPrice = orderItem.getProduct().getPrice() * orderItem.getQuantity();
            grandTotal += totalPrice;
            order.setTotalPrice(grandTotal);
        }
        order.setCompleted(true);
        orderRepository.save(order);

        ConfirmOrderResponseDto responseDto = new ConfirmOrderResponseDto();
        responseDto.setRestaurantName(order.getRestaurant().getName());
        responseDto.setCustomerName(order.getUser().getFullname());
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
        responseDto.setCompleted(order.isCompleted());

        return responseDto;
    }
}

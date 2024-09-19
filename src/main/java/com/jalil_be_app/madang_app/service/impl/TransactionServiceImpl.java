package com.jalil_be_app.madang_app.service.impl;

import com.jalil_be_app.madang_app.dto.transactionDto.request.CreateTransactionRequestDto;
import com.jalil_be_app.madang_app.dto.transactionDto.response.CreateTransactionResponseDto;
import com.jalil_be_app.madang_app.model.entity.Product;
import com.jalil_be_app.madang_app.model.entity.Seat;
import com.jalil_be_app.madang_app.model.entity.Transaction;
import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.enums.PaymentMethod;
import com.jalil_be_app.madang_app.model.enums.SeatCategory;
import com.jalil_be_app.madang_app.repository.ProductRepository;
import com.jalil_be_app.madang_app.repository.SeatRepository;
import com.jalil_be_app.madang_app.repository.TransactionRepository;
import com.jalil_be_app.madang_app.repository.UserRepository;
import com.jalil_be_app.madang_app.service.SeatService;
import com.jalil_be_app.madang_app.service.TransactionService;
import com.jalil_be_app.madang_app.service.jwt.JwtService;
import com.jalil_be_app.madang_app.utils.GenerateTransactionNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    SeatService seatService;
    @Autowired
    JwtService jwtService;

    @Override
    public CreateTransactionResponseDto create(String token, CreateTransactionRequestDto createTransactionRequestDto) {
        String jwtToken = token.substring("Bearer ".length());
        String userId = jwtService.getId(jwtToken);
        UUID userIdFromString = UUID.fromString(userId);

        User existingUser = userRepository.findById(userIdFromString).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found")
        );

        Transaction transaction = new Transaction();
        transaction.setSeat(seatService.findByCategory(createTransactionRequestDto.getSeatCategory()));
        transaction.setUser(existingUser);

        Product existingProduct = productRepository.findById(createTransactionRequestDto.getProductId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found")
        );
        transaction.setProduct(existingProduct);

        transaction.setTotalPrice(existingProduct.getPrice());

        transaction.setPaymentMethod(selectPaymentMethod(createTransactionRequestDto.getPaymentMethod()));
        String transactionNumber = GenerateTransactionNumber.generateTransactionNumber(transaction.getSeat().getCategory());
        transaction.setTransactionNumber(transactionNumber);
        transactionRepository.save(transaction);

        CreateTransactionResponseDto responseDto = new CreateTransactionResponseDto();
        responseDto.setTransactionNumber(transaction.getTransactionNumber());
        responseDto.setUsername(existingUser.getUsername());
        responseDto.setSeatName(transaction.getSeat().getName());
        responseDto.setProductName(existingProduct.getName());
        responseDto.setTotalPrice(transaction.getTotalPrice());
        responseDto.setPaymentMethod(transaction.getPaymentMethod().toString());
        return responseDto;
    }

    @Override
    public PaymentMethod selectPaymentMethod(String paymentName) {
        if (paymentName.equalsIgnoreCase("transfer bank") || paymentName.equalsIgnoreCase("mbanking")) {
            return PaymentMethod.TRANSFER_BANK;
        } else if (paymentName.equalsIgnoreCase("shopeepay")){
            return PaymentMethod.SHOPEEPAY;
        } else if (paymentName.equalsIgnoreCase("gopay")) {
            return PaymentMethod.GOPAY;
        } else if (paymentName.equalsIgnoreCase("dana")){
            return PaymentMethod.DANA;
        } else {
            return PaymentMethod.CASH;
        }
    }
}

package com.jalil_be_app.madang_app.model.entity;

import com.jalil_be_app.madang_app.model.entity.account.User;
import com.jalil_be_app.madang_app.model.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "transaction_number")
    private String transactionNumber;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Product.class)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Seat.class)
    @JoinColumn(name = "seat_id")
    private Seat seat;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "payment_method")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
}

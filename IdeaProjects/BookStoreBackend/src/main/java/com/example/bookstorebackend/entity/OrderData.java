package com.example.bookstorebackend.entity;

import com.example.bookstorebackend.dto.OrderDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "order_data")
@NoArgsConstructor
public @Data
class OrderData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name="price")
    private int price;

    @Column(name="quantity")
    private int quantity;

    @Column(name="address")
    private String address;

    @Column(name="user_id")
    private Long userId;

    @Column(name="book_id")
    private Long bookId;

    @Column(name="cancel")
    private boolean cancel;

    // constructor invoked when the order is placed
    public OrderData(OrderDTO orderDTO) {
        this.updateOrderData(orderDTO);
    }

    private void updateOrderData(OrderDTO orderDTO) {
        this.registerOrderData(orderDTO);
    }

    private void registerOrderData(OrderDTO orderDTO) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.price = price;
        this.quantity = quantity;
        this.address = address;
        this.userId = userId;
        this.bookId = bookId;
        this.cancel = cancel;
    }
}


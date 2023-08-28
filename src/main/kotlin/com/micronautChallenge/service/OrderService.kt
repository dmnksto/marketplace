package com.micronautChallenge.service

import com.micronautChallenge.model.Order
import jakarta.inject.Singleton
import repository.OrderRepository

@Singleton
class OrderService (
    private val orderRepository: OrderRepository
        ) {
    fun create(order : Order) : Order =
        orderRepository.save(order)

    fun getAll(): List<Order> =     //for testing
        orderRepository.findAll()
            .toList()
}
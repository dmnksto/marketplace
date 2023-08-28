package com.micronautChallenge.controller

import com.micronautChallenge.model.Order
import com.micronautChallenge.request.OrderRequest
import com.micronautChallenge.service.OrderService
import com.micronautChallenge.service.ProductService
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.validation.Validated
import jakarta.validation.Valid

@Validated
@Controller("/orders")
class OrderController (
    private val orderService: OrderService,
    private val productService: ProductService
        ){

    @Post
    fun placeOrder(@Body @Valid orderRequest: OrderRequest): Order {
        productService.getById(orderRequest.productId)
        return orderService.create(
            order = orderRequest.toModel()
        )
    }

    @Get
    fun getAll() =
        orderService.getAll()

    private fun OrderRequest.toModel(): Order =
        Order(
            productId = this.productId,
            quantity = this.quantity
        )
}
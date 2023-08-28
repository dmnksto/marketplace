package com.micronautChallenge

import com.micronautChallenge.model.Order
import com.micronautChallenge.model.Product
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test


@MicronautTest
class OrderControllerTest (@Client("/") val client: HttpClient) {

    private lateinit var testProduct: HttpResponse<Product>

    @Test
    fun testPostOrder() {
        val product = HttpRequest.POST("/products", mapOf("name" to "bread", "description" to "wheat", "price" to 5))
        testProduct = client.toBlocking().exchange(product, Product::class.java)

        val request = HttpRequest.POST("/orders", mapOf("productId" to testProduct.body().id, "quantity" to 5))
        val response = client.toBlocking().exchange(request, Order::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(response.body().productId, testProduct.body().id)
    }
}
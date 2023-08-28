package com.micronautChallenge

import com.micronautChallenge.model.Product
import io.micronaut.core.type.Argument
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.http.client.exceptions.HttpClientResponseException
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

@MicronautTest
class ProductControllerTest (@Client("/") val client: HttpClient) {

    private lateinit var testProduct: HttpResponse<Product>

    @BeforeEach
    fun setTestProduct() {
        val request = HttpRequest.POST("/products", mapOf("name" to "butter", "description" to "milk product", "price" to 2))
        testProduct = client.toBlocking().exchange(request, Product::class.java)
    }

    @AfterEach
    fun clear() {
        val request = HttpRequest.DELETE<Any>("/products/" + testProduct.body().id)
        client.toBlocking().exchange<Any, Any>(request)
    }

    @Test
    fun testAddProduct()  {
        val request = HttpRequest.POST("/products", mapOf("name" to "bread", "description" to "wheat", "price" to 5))
        val response = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, response.status)
        assertEquals(response.body().name, "bread")

        val deleteAdded = HttpRequest.DELETE<Any>("/products/" + response.body().id)
        client.toBlocking().exchange<Any, Any>(deleteAdded)
    }

    @Test
    fun addProductInvalid() {
        val request = HttpRequest.POST("/products", mapOf("name" to "", "description" to "", "price" to -10))
        val thrown = assertThrows<HttpClientResponseException> { client.toBlocking().exchange(request, Product::class.java) }

        assertEquals(HttpStatus.BAD_REQUEST, thrown.status)
    }

    @Test
    fun testGetById() {
        HttpRequest.POST("/products", testProduct)

        val request = HttpRequest.GET<Any>("/products/" + testProduct.body().id)
        val received = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, received.status)
        assertEquals(testProduct.body(), received.body())

    }

    @Test
    fun testGetByIdInvalid() {
        val request = HttpRequest.GET<Any>("/products/invalidID")
        val thrown = assertThrows<HttpClientResponseException> { client.toBlocking().exchange(request, Product::class.java) }

        assertEquals(HttpStatus.BAD_REQUEST, thrown.status)
    }

    @Test
    fun testGetAll() {
        var request = HttpRequest.POST("/products", mapOf("name" to "bread", "description" to "wheat", "price" to 5))
        val product2 = client.toBlocking().exchange(request, Product::class.java)

        request = HttpRequest.GET("/products/")
        val received = client.toBlocking().exchange(request, Argument.listOf(Product::class.java))

        assertEquals(HttpStatus.OK, received.status)
        assertEquals(2, received.body().size)
        assertTrue(received.body().contains(testProduct.body()))
        assertTrue(received.body().contains(product2.body()))

        val deleteAdded = HttpRequest.DELETE<Any>("/products/" + product2.body().id)
        client.toBlocking().exchange<Any, Any>(deleteAdded)

    }

    @Test
    fun testUpdate() {
        val request = HttpRequest.PUT("products/" + testProduct.body().id, mapOf("name" to "milk", "description" to "still milk", "price" to 1))
        val updated = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, updated.status)
        assertEquals(testProduct.body().id, updated.body().id)
        assertNotEquals(testProduct.body().price, updated.body().price)
    }

    @Test
    fun testRemoveProduct() {
        var request = HttpRequest.POST("/products", mapOf("name" to "bread", "description" to "wheat", "price" to 5))
        var response = client.toBlocking().exchange(request, Product::class.java)

        val id = response.body().id

        request = HttpRequest.DELETE("/products/$id")
        response = client.toBlocking().exchange(request, Product::class.java)

        assertEquals(HttpStatus.OK, response.status)

        request = HttpRequest.GET("/products/$id")
        val thrown = assertThrows<HttpClientResponseException> { response = client.toBlocking().exchange(request, Product::class.java) }

        assertEquals(thrown.status, HttpStatus.NOT_FOUND)
        assertNotNull(thrown.response)
    }
}
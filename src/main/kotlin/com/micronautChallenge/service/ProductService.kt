package com.micronautChallenge.service

import com.micronautChallenge.model.Product
import com.micronautChallenge.repository.ProductRepository
import io.micronaut.http.HttpStatus
import io.micronaut.http.exceptions.HttpStatusException
import jakarta.inject.Singleton


@Singleton
class ProductService (
    private val productRepository: ProductRepository
        ) {

    private val hexRegex = """^[0-9a-fA-F]{24}$""".toRegex() //Needed Format for getById to work properly, netter Hinweis von Matteo

    fun create(product : Product): Product =
        productRepository.save(product)

    fun getAll(): List<Product> =
        productRepository.findAll()
            .toList()

    fun getById(id: String): Product {
        if (!hexRegex.matches(id)) {
            throw HttpStatusException(HttpStatus.BAD_REQUEST,
                "Product ID does not have the correct format. A valid ID should consist of exactly 24 characters, each representing a valid hexadecimal digit (0-9, a-f, or A-F).")
        }
        return productRepository.findById(id)
        .orElseThrow { HttpStatusException(HttpStatus.NOT_FOUND, "Product with ID $id was not found.") }
    }

    fun update(id: String, product: Product): Product {
        val existing = getById(id)
        val updated = product.copy(id = existing.id)
        return productRepository.update(updated)
    }

    fun delete(id: String) {
        val existing = getById(id)
        productRepository.delete(existing)
    }
}
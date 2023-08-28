package com.micronautChallenge.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable.Deserializable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero


@Deserializable
@Introspected
data class OrderRequest(
    @field:NotBlank(message = "ProductId must not be empty!")
    val productId: String,
    @field:PositiveOrZero(message = "Quantity must not be negative!")
    val quantity: Int
)

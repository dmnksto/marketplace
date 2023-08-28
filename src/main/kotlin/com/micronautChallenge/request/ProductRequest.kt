package com.micronautChallenge.request

import io.micronaut.core.annotation.Introspected
import io.micronaut.serde.annotation.Serdeable.Deserializable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero

@Deserializable
@Introspected
data class ProductRequest(
    @field:NotBlank(message = "Name must not be empty!")
    val name : String,
    //Beschreibung darf auch leer bleiben, nicht essenziell für Produkt
    val description : String,
    @field:PositiveOrZero(message = "Prize must not be negative!")
    val price : Int,
)

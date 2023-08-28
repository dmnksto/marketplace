package com.micronautChallenge.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.validation.Validated
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import java.text.DecimalFormat

@MappedEntity
data class Product(
    @field:Id
    @field:GeneratedValue
    val id : String? = null,
    var name : String,
    var description : String,
    var price : Int,
)

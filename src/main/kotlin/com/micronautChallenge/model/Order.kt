package com.micronautChallenge.model

import io.micronaut.data.annotation.GeneratedValue
import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity

@MappedEntity
data class Order(
    @field:Id
    @field:GeneratedValue
    val id : String? = null,
    val productId : String,
    var quantity : Int

)

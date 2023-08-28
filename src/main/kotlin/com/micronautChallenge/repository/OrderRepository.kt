package repository

import com.micronautChallenge.model.Order
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository


@MongoRepository
interface OrderRepository : CrudRepository<Order, String>
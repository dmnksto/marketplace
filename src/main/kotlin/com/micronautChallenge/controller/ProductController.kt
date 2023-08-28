package micronautChallenge.controller

import com.micronautChallenge.model.Product
import io.micronaut.http.MediaType
import io.micronaut.http.annotation.*
import io.micronaut.validation.Validated
import jakarta.validation.Valid
import com.micronautChallenge.request.ProductRequest
import com.micronautChallenge.service.ProductService

@Validated
@Controller("/products")
class ProductController (
    private val productService: ProductService
        ){

    @Post
    @Consumes(MediaType.APPLICATION_JSON)
    fun addProduct(@Body @Valid productRequest: ProductRequest) =
        productService.create(
            product = productRequest.toModel()
        )

    @Get
    fun getAll() =
        productService.getAll()

    @Get("/{product_id}")
    fun getById(@PathVariable("product_id") id: String) =
        productService.getById(id)

    @Put("/{product_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    fun update(
        @PathVariable("product_id") id: String,
        @Body @Valid productRequest: ProductRequest
    ) =
        productService.update(id, product = productRequest.toModel())

    @Delete("/{product_id}")
    fun deleteProduct(@PathVariable("product_id") id: String) =
        productService.delete(id)

    private fun ProductRequest.toModel(): Product =
        Product(
            name = this.name,
            description = this.description,
            price = this.price
        )
}
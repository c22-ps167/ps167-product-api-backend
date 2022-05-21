package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto
import c22.ps167.backendrestfulapi.service.ProductService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(private val productService: ProductService) {

    @PostMapping(
        value = ["/api/p"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductDto> {
        val response = productService.create(body)
        return WebResponse(
            code = 200,
            status = "success",
            data = response
        )
    }

}
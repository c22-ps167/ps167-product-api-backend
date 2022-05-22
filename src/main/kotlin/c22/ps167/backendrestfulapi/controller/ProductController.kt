package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.UpdateProductRequest
import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto
import c22.ps167.backendrestfulapi.service.ProductService
import org.springframework.web.bind.annotation.*

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
            status = "OK",
            data = response
        )
    }

    @PostMapping(
        value = ["/api/p/"],
        produces = ["application/json"],
        consumes = ["application/json"]
    )
    fun createBulkProduct(@RequestBody body: List<CreateProductRequest>): WebResponse<String> {
        val response = productService.createBulk(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "$response products added"
        )
    }

    @GetMapping(
        value = ["/api/p/{id}"],
        produces = ["application/json"]
    )
    fun getProduct(@PathVariable("id") id: String): WebResponse<ProductDto> {
        val response = productService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @DeleteMapping(
        value = ["/api/p/"],
        produces = ["application/json"]
    )
    fun deleteAllProduct(): WebResponse<String> {
        val response = productService.deleteAll()
        return WebResponse(
            code = 200,
            status = "OK",
            data = "$response products deleted"
        )
    }

    @PutMapping(
        value = ["/api/p/{id}"],
        produces = ["application/json"]
    )
    fun updateProduct(
        @PathVariable("id") id: String,
        @RequestBody body: UpdateProductRequest
    ): WebResponse<ProductDto> {
        val response = productService.update(id, body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @DeleteMapping(
        value = ["/api/p/{id}"],
        produces = ["application/json"]
    )
    fun deleteProduct(
        @PathVariable("id") id: String
    ): WebResponse<String> {
        productService.delete(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "$id deleted"
        )
    }
}
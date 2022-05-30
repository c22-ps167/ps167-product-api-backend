package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.UpdateProductRequest
import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto
import c22.ps167.backendrestfulapi.service.ProductService
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.function.EntityResponse

@RestController
@RequestMapping(
    value = ["/api/p"],
    produces = ["application/json"]
)
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun createProduct(@RequestBody body: CreateProductRequest): WebResponse<ProductDto> {
        val response = productService.create(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @PostMapping("/")
    fun createBulkProduct(@RequestBody body: List<CreateProductRequest>): WebResponse<String> {
        val response = productService.createBulk(body)
        return WebResponse(
            code = 200,
            status = "OK",
            data = "$response products added"
        )
    }

    @GetMapping("/{id}")
    fun getProduct(@PathVariable("id") id: String): WebResponse<ProductDto> {
        val response = productService.get(id)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @GetMapping
    fun listByName(
        @RequestParam(value = "name") name: String,
        @RequestParam(value = "page", defaultValue = "1") page: Int,
        @RequestParam(value = "size", defaultValue = "10") size: Int
    ): WebResponse<List<ProductDto>> {
        val response = productService.listByName(name, page, size)
        return WebResponse(
            code = 200,
            status = "OK",
            data = response
        )
    }

    @PutMapping("/{id}")
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

    @DeleteMapping("/")
    fun deleteAllProduct(): WebResponse<String> {
        val response = productService.deleteAll()
        return WebResponse(
            code = 200,
            status = "OK",
            data = "$response products deleted"
        )
    }

    @DeleteMapping("/{id}")
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
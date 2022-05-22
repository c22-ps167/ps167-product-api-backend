package c22.ps167.backendrestfulapi.service

import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto

interface ProductService {

    fun create(request: CreateProductRequest): ProductDto

    fun createBulk(request: List<CreateProductRequest>): Int

    fun get(id: String): ProductDto
}
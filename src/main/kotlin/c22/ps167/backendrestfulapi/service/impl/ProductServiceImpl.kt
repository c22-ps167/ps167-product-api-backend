package c22.ps167.backendrestfulapi.service.impl

import c22.ps167.backendrestfulapi.data.entity.NutritionFact
import c22.ps167.backendrestfulapi.data.entity.Product
import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto
import c22.ps167.backendrestfulapi.data.repository.ProductRepository
import c22.ps167.backendrestfulapi.service.ProductService
import c22.ps167.backendrestfulapi.util.error.AlreadyExistException
import c22.ps167.backendrestfulapi.util.validation.ValidationUtil
import org.springframework.stereotype.Service
import java.util.*

@Service
class ProductServiceImpl(
    private val validationUtil: ValidationUtil,
    private val productRepository: ProductRepository
) : ProductService {
    override fun create(request: CreateProductRequest): ProductDto {
        validationUtil.validate(request)
        validationUtil.validate(request.nutritionFact)

        if (productRepository.existsById(request.id!!)) {
            throw AlreadyExistException()
        }

        val product = Product(
            id = request.id,
            name = request.name!!,
            createdAt = Date(),
            updatedAt = null
        )
        val nutritionFact = NutritionFact(
            calories = request.nutritionFact.calories!!,
            totalFat = request.nutritionFact.totalFat!!,
            saturatedFat = request.nutritionFact.saturatedFat!!,
            protein = request.nutritionFact.protein!!,
            totalCarbohydrate = request.nutritionFact.totalCarbohydrate!!,
            sugar = request.nutritionFact.sugar!!,
            sodium = request.nutritionFact.sodium!!
        )

        nutritionFact.product = product
        product.nutritionFact = nutritionFact
        productRepository.save(product)

        return product.toDto()
    }

    private fun Product.toDto(): ProductDto {
        return ProductDto(
            id = this.id,
            name = this.name,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            nutritionFact = this.nutritionFact!!.toDto()
        )
    }

    private fun NutritionFact.toDto(): ProductDto.NutritionFactDto {
        return ProductDto.NutritionFactDto(
            calories = this.calories,
            totalFat = this.totalFat,
            saturatedFat = this.saturatedFat,
            protein = this.protein,
            totalCarbohydrate = this.totalCarbohydrate,
            sugar = this.sugar,
            sodium = this.sodium
        )
    }
}
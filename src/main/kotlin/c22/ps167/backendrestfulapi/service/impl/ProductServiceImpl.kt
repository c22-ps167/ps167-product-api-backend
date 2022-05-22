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
    val validationUtil: ValidationUtil,
    val productRepository: ProductRepository
) : ProductService {
    override fun create(request: CreateProductRequest): ProductDto {
        validationUtil.validate(request)

        if (productRepository.existsById(request.id!!)) {
            throw AlreadyExistException()
        }

        val product = convertRequestToProductAndNutritionFact(request)
        productRepository.save(product)

        return product.toDto()
    }

    override fun createBulk(request: List<CreateProductRequest>): Int {
        productRepository.saveAll(
            request.map {
                validationUtil.validate(it)
                convertRequestToProductAndNutritionFact(it)
            }
        )
        return request.size
    }

    private fun convertRequestToProductAndNutritionFact(request: CreateProductRequest): Product {
        val product = Product(
            id = request.id!!,
            name = request.name!!,
            createdAt = Date(),
            updatedAt = null
        )
        val nutritionFact = NutritionFact(
            calories = request.calories!!,
            totalFat = request.totalFat!!,
            saturatedFat = request.saturatedFat!!,
            protein = request.protein!!,
            totalCarbohydrate = request.totalCarbohydrate!!,
            sugar = request.sugar!!,
            sodium = request.sodium!!
        )

        nutritionFact.product = product
        product.nutritionFact = nutritionFact

        return product
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
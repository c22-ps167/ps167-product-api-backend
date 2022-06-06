package c22.ps167.backendrestfulapi.service.impl

import c22.ps167.backendrestfulapi.data.entity.NutritionFact
import c22.ps167.backendrestfulapi.data.entity.Product
import c22.ps167.backendrestfulapi.data.model.CreateProductRequest
import c22.ps167.backendrestfulapi.data.model.UpdateProductRequest
import c22.ps167.backendrestfulapi.data.model.dto.ProductDto
import c22.ps167.backendrestfulapi.data.repository.ProductRepository
import c22.ps167.backendrestfulapi.service.ProductService
import c22.ps167.backendrestfulapi.util.error.AlreadyExistException
import c22.ps167.backendrestfulapi.util.error.NotFoundException
import c22.ps167.backendrestfulapi.util.validation.ValidationUtil
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors

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

        return productRepository.save(request.toProduct()).toDto()
    }

    override fun createBulk(request: List<CreateProductRequest>): Int {
        val products = request.map {
            validationUtil.validate(it)
            it.toProduct()
        }

        val result = mutableListOf<Product>()

        products.forEach {
            if (!productRepository.existsById(it.id)) {
                result.add(it)
            }
        }

        if (result.isEmpty()) {
            throw AlreadyExistException()
        }

        productRepository.saveAll(result)

        return result.size
    }

    override fun get(id: String): ProductDto {
        return findProductByIdOrThrowNotFound(id).toDto()
    }

    override fun deleteAll(): Int {
        val counter = productRepository.count()
        productRepository.deleteAll()
        return counter.toInt()
    }

    override fun update(id: String, request: UpdateProductRequest): ProductDto {
        val product = findProductByIdOrThrowNotFound(id)

        product.apply {
            name = request.name ?: product.name
            url = request.url ?: product.url
            updatedAt = Date()
        }

        product.nutritionFact?.apply {
            calories = request.calories ?: product.nutritionFact!!.calories
            totalFat = request.totalFat ?: product.nutritionFact!!.totalFat
            saturatedFat = request.saturatedFat ?: product.nutritionFact!!.saturatedFat
            protein = request.protein ?: product.nutritionFact!!.protein
            totalCarbohydrate = request.totalCarbohydrate ?: product.nutritionFact!!.totalCarbohydrate
            sugar = request.sugar ?: product.nutritionFact!!.sugar
            sodium = request.sodium ?: product.nutritionFact!!.sodium
            servingSize = request.servingSize ?: product.nutritionFact!!.servingSize
        }

        return productRepository.save(product).toDto()
    }

    override fun delete(id: String) {
        findProductByIdOrThrowNotFound(id)
        productRepository.deleteById(id)
    }

    override fun listByName(name: String, page: Int, size: Int): List<ProductDto> {
        val mPage = productRepository.findAllByProductName(name.lowercase(), PageRequest.of(page-1, size))
        val products: List<Product> = mPage.get().collect(Collectors.toList())
        return products.map { it.toDto() }
    }


    private fun CreateProductRequest.toProduct(): Product {
        val product = Product(
            id = this.id!!,
            name = this.name!!,
            createdAt = Date(),
            updatedAt = null,
            url = this.url!!
        )
        val nutritionFact = NutritionFact(
            calories = this.calories!!,
            totalFat = this.totalFat!!,
            saturatedFat = this.saturatedFat!!,
            protein = this.protein!!,
            totalCarbohydrate = this.totalCarbohydrate!!,
            sugar = this.sugar!!,
            sodium = this.sodium!!,
            servingSize = this.servingSize!!
        )

        nutritionFact.product = product
        product.nutritionFact = nutritionFact

        return product
    }

    private fun findProductByIdOrThrowNotFound(id: String): Product {
        val product = productRepository.findByIdOrNull(id)
        if (product == null) {
            throw NotFoundException()
        } else {
            return product
        }
    }

    private fun Product.toDto(): ProductDto {
        return ProductDto(
            id = this.id,
            name = this.name,
            createdAt = this.createdAt,
            updatedAt = this.updatedAt,
            nutritionFact = this.nutritionFact!!.toDto(),
            url = this.url
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
            sodium = this.sodium,
            servingSize = this.servingSize
        )
    }

}
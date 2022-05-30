package c22.ps167.backendrestfulapi.data.model.dto

import java.io.Serializable
import java.util.*

data class ProductDto(
    val id: String? = null,
    val name: String? = null,
    val createdAt: Date? = null,
    val updatedAt: Date? = null,
    val nutritionFact: NutritionFactDto? = null
) : Serializable {
    data class NutritionFactDto(
        val calories: Int? = null,
        val totalFat: Int? = null,
        val saturatedFat: Int? = null,
        val protein: Int? = null,
        val totalCarbohydrate: Int? = null,
        val sugar: Int? = null,
        val sodium: Int? = null,
        val servingSize: Int? = null
    ) : Serializable
}

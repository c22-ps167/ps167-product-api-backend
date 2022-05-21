package c22.ps167.backendrestfulapi.data.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateProductRequest(

    @NotBlank
    val id: String?,

    @NotBlank
    val name: String?,

    @NotNull
    val nutritionFact: CreateProductNutritionFactRequest

)
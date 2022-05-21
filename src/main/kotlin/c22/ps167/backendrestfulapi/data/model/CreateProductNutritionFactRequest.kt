package c22.ps167.backendrestfulapi.data.model

import javax.validation.constraints.NotNull

data class CreateProductNutritionFactRequest(

    @NotNull
    val calories: Int?,

    @NotNull
    val totalFat: Int?,

    @NotNull
    val saturatedFat: Int?,

    @NotNull
    val protein: Int?,

    @NotNull
    val totalCarbohydrate: Int?,

    @NotNull
    val sugar: Int?,

    @NotNull
    val sodium: Int?

)

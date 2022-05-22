package c22.ps167.backendrestfulapi.data.model

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

data class CreateProductRequest(

    @field:NotBlank
    val id: String?,

    @field:NotBlank
    val name: String?,

    @field:NotNull
    val calories: Int?,

    @field:NotNull
    val totalFat: Int?,

    @field:NotNull
    val saturatedFat: Int?,

    @field:NotNull
    val protein: Int?,

    @field:NotNull
    val totalCarbohydrate: Int?,

    @field:NotNull
    val sugar: Int?,

    @field:NotNull
    val sodium: Int?

)
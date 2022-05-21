package c22.ps167.backendrestfulapi.data.model

data class WebResponse<T>(
    val code: Int,
    val status: String,
    val data: T
)

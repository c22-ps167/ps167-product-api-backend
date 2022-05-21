package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.util.error.AlreadyExistException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [AlreadyExistException::class])
    fun alreadyExist(alreadyExistException: AlreadyExistException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = "Product Already Exist"
        )
    }

}
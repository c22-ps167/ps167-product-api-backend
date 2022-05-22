package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.util.error.AlreadyExistException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.validation.ConstraintViolationException

@RestControllerAdvice
class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [AlreadyExistException::class])
    fun alreadyExist(): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = "Product Already Exist"
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun invalidRequest(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "BAD REQUEST",
            data = constraintViolationException.message!!
        )
    }
}
package c22.ps167.backendrestfulapi.controller

import c22.ps167.backendrestfulapi.data.model.WebResponse
import c22.ps167.backendrestfulapi.util.error.AlreadyExistException
import c22.ps167.backendrestfulapi.util.error.NotFoundException
import c22.ps167.backendrestfulapi.util.error.UnauthorizedException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import javax.validation.ConstraintViolationException

@EnableWebMvc
@RestControllerAdvice
class ErrorController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [AlreadyExistException::class])
    fun alreadyExist(): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = "Product Already Exist"
        )
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun invalidRequest(constraintViolationException: ConstraintViolationException): WebResponse<String> {
        return WebResponse(
            code = 400,
            status = "Bad Request",
            data = constraintViolationException.message!!
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun notFound(): WebResponse<String> {
        return WebResponse(
            code = 404,
            status = "Not Found",
            data = "Not Found"
        )
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = [UnauthorizedException::class])
    fun unauthorized(unauthorizedException: UnauthorizedException): WebResponse<String?> {
        return WebResponse(
            code = 400,
            status = "UNAUTHORIZED",
            data = unauthorizedException.message
        )
    }
}
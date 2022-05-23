package c22.ps167.backendrestfulapi.auth

import c22.ps167.backendrestfulapi.util.error.UnauthorizedException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.ui.ModelMap
import org.springframework.web.context.request.WebRequest
import org.springframework.web.context.request.WebRequestInterceptor

@Component
class ApiKeyInterceptor : WebRequestInterceptor {

    @Value("\${c22.ps167.backendrestfulapi.auth.api-key}")
    private lateinit var apiKey: String

    @Value("\${c22.ps167.backendrestfulapi.auth.header-name}")
    private lateinit var headerName: String

    override fun preHandle(request: WebRequest) {
        val header = request.getHeader(headerName) ?: throw UnauthorizedException("Wrong or missing header name")

        if (header != apiKey) {
            throw UnauthorizedException("Wrong or missing api key")
        }
    }

    override fun postHandle(request: WebRequest, model: ModelMap?) {
        //
    }

    override fun afterCompletion(request: WebRequest, ex: Exception?) {
        //
    }

}
package moe.sola.http.interceptor

import moe.sola.http.log
import moe.sola.http.request.Request
import moe.sola.http.request.Response
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/5/19, 周二
 * @description:
 **/
class LoginInterceptor: Interceptor {

    override fun handleBefore(request: Request): Request {
        request.printRequest()
        return request
    }

    override fun handleAfter(response: Response): Response {
        response.printResponse()
        return response
    }

    fun Request.printRequest() {
        log("Request(${url}) >>>")
        log("method:$method")
        log("headers:")
        headers.forEach {
            log("   {${it.key}: [${it.value.joinToString(",")}]}")
        }
        log("<<<")
        log("")
        log("")
    }

    fun Response.printResponse() {
        val byteArrayOutputStream = ByteArrayOutputStream()
        this.content.copyTo(byteArrayOutputStream)
        log("Response($url) >>>")
        log("code:" + this.responseCode)
        log("message:" + this.responseMessage)
        log("data:" + ByteArrayInputStream(byteArrayOutputStream.toByteArray()).bufferedReader().readText())
        log("<<<")
        log("")
        log("")
    }
}
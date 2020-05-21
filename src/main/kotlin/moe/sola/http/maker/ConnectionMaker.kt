package moe.sola.http.maker

import moe.sola.http.ext.createConnection
import moe.sola.http.ext.doConnection
import moe.sola.http.interceptor.Interceptor
import moe.sola.http.interceptor.LoginInterceptor
import moe.sola.http.request.Header
import moe.sola.http.request.Param
import moe.sola.http.request.Request
import moe.sola.http.request.Response
import java.io.DataOutputStream
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
open class ConnectionMaker : IConnectionMaker {

    private var interceptorList: MutableList<Interceptor> = mutableListOf(LoginInterceptor())

    override fun String.createGet(action: Request.() -> Unit): Response {
        return createMethod("GET", action)
    }

    override fun String.createPost(action: Request.() -> Unit): Response {
        return createMethod("POST", action)
    }

    override fun String.createMethod(method: String, action: Request.() -> Unit): Response {
        val conection = this.createConnection().apply {
            requestMethod = method

            var request = Request(this@createMethod, method, emptyList(), emptyList())
            action.invoke(request)

            request = request.beforeInterceptorsActions()
            request.headers.forEach {
                addRequestProperty(it.key, it.value.joinToString(","))
            }
            val paramText = request.params.joinToString("&") { it.key + "=" + it.value }
            if (paramText.isNotEmpty()){
                doOutput = true
                val out = DataOutputStream(outputStream)
                out.writeBytes(paramText)
            }



        }.doConnection()

        conection.run {
            var resp = Response(
                this@createMethod,
                this.responseCode,
                this.responseMessage,
                this.inputStream
            )
            resp = resp.afterInterceptorsActions()
            return resp
        }
    }

    private fun Request.beforeInterceptorsActions(): Request {
        var request = this
        interceptorList.forEach { request = it.handleBefore(request) }
        return request
    }

    private fun Response.afterInterceptorsActions(): Response {
        var response = this
        interceptorList.forEach { response = it.handleAfter(response) }
        return response
    }

    override fun addIntercept(vararg interceptor: Interceptor) {
        interceptor.forEach {
            interceptorList.add(it)
        }
    }

}


package moe.sola.http.maker

import moe.sola.http.ext.createConnection
import moe.sola.http.ext.doConnection
import moe.sola.http.interceptor.Interceptor
import moe.sola.http.interceptor.LoginInterceptor
import moe.sola.http.request.Header
import moe.sola.http.request.Param
import moe.sola.http.request.Request
import moe.sola.http.request.Response
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
open class ConnectionMaker : IConnectionMaker {

    private var interceptorList: MutableList<Interceptor> = mutableListOf(LoginInterceptor())

    override fun String.createGet(action: Request.() -> Unit): HttpURLConnection {
        return createMethod("GET", action)
    }

    override fun String.createPost(action: Request.() -> Unit): HttpURLConnection {
        return createMethod("POST", action)
    }

    override fun String.createMethod(method: String, action: Request.() -> Unit): HttpURLConnection {
        val conection = this.createConnection().apply {
            requestMethod = method

            val headers = mutableListOf<Header>()
            val params = mutableListOf<Param>()
            val request = Request(this@createMethod, method, headers, params)
            action.invoke(request)


            request.beforeInterceptorsActions()


        }.doConnection()

        conection.run {
            val resp = Response(
                this@createMethod,
                this.responseCode,
                this.responseMessage,
                this.inputStream.bufferedReader().readText()
            )
            resp.afterInterceptorsActions()
        }

        return conection

    }

    private fun Request.beforeInterceptorsActions() {
        interceptorList.forEach { it.handleBefore(this) }
    }

    private fun Response.afterInterceptorsActions() {
        interceptorList.forEach { it.handleAfter(this) }
    }

    override fun addIntercept(vararg interceptor: Interceptor) {
        interceptor.forEach {
            interceptorList.add(it)
        }
    }

}


package moe.sola.http.maker

import moe.sola.http.ext.createConnection
import moe.sola.http.ext.doConnection
import moe.sola.http.interceptor.Interceptor
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
open class ConnectionMaker : IConnectionMaker {

    private var interceptorList: MutableList<Interceptor> = mutableListOf()

    override fun String.createGet(action: HttpURLConnection.() -> Unit): HttpURLConnection {
        return this.createConnection().apply {
            interceptorList.forEach { it.handleBefore(this) }
            requestMethod = "GET"
            action.invoke(this)
        }.doConnection()
    }

    override fun String.createPost(action: HttpURLConnection.() -> Unit): HttpURLConnection {
        return this.createConnection().apply {
            interceptorList.forEach { it.handleBefore(this) }
            requestMethod = "POST"
            action.invoke(this)
        }.doConnection()
    }

    override fun String.createMethod(method: String, action: HttpURLConnection.() -> Unit): HttpURLConnection {
        return this.createConnection().apply {
            interceptorList.forEach { it.handleBefore(this) }
            requestMethod = method
            action.invoke(this)
        }.doConnection()
    }

    override fun addIntercept(vararg interceptor: Interceptor) {
        interceptor.forEach {
            interceptorList.add(it)
        }
    }

}


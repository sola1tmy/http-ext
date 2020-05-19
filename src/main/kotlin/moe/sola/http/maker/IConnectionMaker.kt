package moe.sola.http.maker

import moe.sola.http.ext.createConnection
import moe.sola.http.interceptor.Interceptor
import moe.sola.http.request.Request
import moe.sola.http.request.Response
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
interface IConnectionMaker {

    fun String.createGet(action: Request.()-> Unit): Response

    fun String.createPost(action: Request.()-> Unit): Response

    fun String.createMethod(method: String, action: Request.() -> Unit): Response

    fun addIntercept(vararg interceptor: Interceptor)

}
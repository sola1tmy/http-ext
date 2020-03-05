package moe.sola.http.maker

import moe.sola.http.ext.createConnection
import moe.sola.http.interceptor.Interceptor
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
interface IConnectionMaker {

    fun String.createGet(action: HttpURLConnection.()-> Unit): HttpURLConnection

    fun String.createPost(action: HttpURLConnection.()-> Unit): HttpURLConnection

    fun addIntercept(vararg interceptor: Interceptor)

}
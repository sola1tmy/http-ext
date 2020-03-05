package moe.sola.http.interceptor

import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
interface Interceptor {

    fun handleBefore(urlConnection: HttpURLConnection)

}
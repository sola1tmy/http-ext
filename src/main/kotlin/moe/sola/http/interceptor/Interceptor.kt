package moe.sola.http.interceptor

import moe.sola.http.log
import moe.sola.http.request.Request
import moe.sola.http.request.Response
import java.lang.Exception
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
interface Interceptor {

    fun handleBefore(request: Request): Request {
        return request
    }

    fun handleAfter(response: Response): Response {
        return response
    }
}
package moe.sola.http.request

import java.io.InputStream

/**
 * @author : sola_tmy
 * @since : 2020/5/19, 周二
 * @description:
 **/
data class Response(
    val url: String,
    val responseCode: Int,
    val responseMessage: String,
    val content: InputStream
)
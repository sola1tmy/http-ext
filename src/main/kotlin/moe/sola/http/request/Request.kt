package moe.sola.http.request

/**
 * @author : sola_tmy
 * @since : 2020/5/19, 周二
 * @description:
 **/
data class Request(
    val url: String,
    val method: String,
    val headers: List<Header>,
    val params: List<Param>
)

data class Header(
    val key: String,
    val value: List<String>
)

data class Param(
    val key: String,
    val value: String
)
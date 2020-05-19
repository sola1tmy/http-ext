package moe.sola.http

import moe.sola.http.maker.DefaultMaker
import moe.sola.http.request.Header
import moe.sola.http.request.Request
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
fun String.httpGet(action: Request.() -> Unit = {}): HttpURLConnection {
    with(DefaultMaker) {
        return createGet(action)
    }
}

fun String.httpPost(action: Request.() -> Unit = {}): HttpURLConnection {
    with(DefaultMaker) {
        return createPost(action)
    }
}

fun String.httpConnection(method: String, action: Request.() -> Unit = {}): HttpURLConnection {
    with(DefaultMaker) {
        return createMethod(method, action)
    }
}

@Deprecated("Look at Header")
fun Request.headers(vararg pairs: Pair<String, String>) {
    pairs.forEach {
        this.headers.add(Header(it.first, listOf(it.second)))
    }
}
package moe.sola.http

import moe.sola.http.maker.DefaultMaker
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
fun String.httpGet(action: HttpURLConnection.() -> Unit = {}): HttpURLConnection {
    with(DefaultMaker) {
        return createGet(action)
    }
}

fun String.httpPost(action: HttpURLConnection.() -> Unit = {}): HttpURLConnection {
    with(DefaultMaker) {
        return createPost(action)
    }
}

fun HttpURLConnection.headers(vararg pairs: Pair<String, String>) {
    pairs.forEach {
        this.addRequestProperty(it.first, it.second)
    }
}
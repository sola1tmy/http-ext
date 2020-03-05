package moe.sola.http.result

import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
fun HttpURLConnection.readAsString(): String {
    return String(this.inputStream.readBytes())
}

fun <T> HttpURLConnection.readByAdapter(adapter: IResultAdapter<T>): T {
    return adapter.adapter(this.inputStream)
}
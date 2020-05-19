package moe.sola.http.result

import moe.sola.http.request.Response
import java.net.HttpURLConnection

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
fun Response.readAsString(): String {
    return String(this.content.readBytes())
}

fun <T> Response.readByAdapter(adapter: IResultAdapter<T>): T {
    return adapter.adapter(this.content)
}
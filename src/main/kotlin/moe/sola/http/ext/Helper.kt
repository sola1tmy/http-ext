package moe.sola.http.ext

import java.net.HttpURLConnection
import java.net.URL

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
fun String.createConnection(): HttpURLConnection {
    return URL(this).openConnection() as HttpURLConnection
}

fun HttpURLConnection.doConnection(): HttpURLConnection {
    connect()
    return this
}
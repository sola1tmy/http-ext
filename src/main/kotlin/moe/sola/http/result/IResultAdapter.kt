package moe.sola.http.result

import java.io.InputStream

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
interface IResultAdapter<T> {

    fun adapter(stream: InputStream): T
}
package moe.sola.http

import moe.sola.http.result.IResultAdapter
import java.io.InputStream

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
object StringAdapter: IResultAdapter<String> {

    override fun adapter(stream: InputStream): String {
        return String(stream.readBytes())
    }

}
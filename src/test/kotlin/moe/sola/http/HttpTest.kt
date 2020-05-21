package moe.sola.http

import moe.sola.http.result.readAsString
import moe.sola.http.result.readByAdapter
import org.junit.Test

/**
 * @author : sola_tmy
 * @since : 2020/3/5, 周四
 * @description:
 **/
class HttpTest {

    @Test
    fun httpGetTest() {
        "http://www.baidu.com".httpGet {
//            headers(
//                "header1" to "as"
//            )
        }.readAsString().let { println(it) }
    }

    @Test
    fun adapterTest() {
//        "http://www.baidu.com".httpGet {
//            headers(
//                "header1" to "as"
//            )
//        }.readByAdapter(StringAdapter).let { println(it) }
    }
}
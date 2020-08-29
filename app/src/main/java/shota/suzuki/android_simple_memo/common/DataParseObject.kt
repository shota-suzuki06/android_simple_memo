package shota.suzuki.android_simple_memo.common

import java.text.SimpleDateFormat
import java.util.*

class DataParseObject {

    companion object {
        fun dateToStringParse(date: Date): String {
            val sdf = SimpleDateFormat("yyyy/MM/dd HH:mm")
            return sdf.format(date)
        }
    }
}
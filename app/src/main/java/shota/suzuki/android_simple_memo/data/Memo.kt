package shota.suzuki.android_simple_memo.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "memo_table")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val content: String,
    val date: Date
)
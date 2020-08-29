package shota.suzuki.android_simple_memo.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MemoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(memo: Memo)

    @Query("SELECT * FROM memo_table ORDER BY date ASC")
    fun getAllData(): LiveData<List<Memo>>

    @Update
    suspend fun updateData(memo: Memo)

    @Delete
    suspend fun deleteData(memo: Memo)

}
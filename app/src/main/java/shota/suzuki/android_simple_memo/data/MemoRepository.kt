package shota.suzuki.android_simple_memo.data

import androidx.lifecycle.LiveData

class MemoRepository(private val memoDao: MemoDao) {

    val readAllData: LiveData<List<Memo>> = memoDao.getAllData()

    suspend fun insertData(memo: Memo) {
        memoDao.insertData(memo)
    }

    suspend fun updateData(memo: Memo) {
        memoDao.updateData(memo)
    }

    suspend fun deleteData(memo: Memo) {
        memoDao.deleteData(memo)
    }

}

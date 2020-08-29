package shota.suzuki.android_simple_memo.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MemoViewModel(application: Application): AndroidViewModel(application) {

    var isEdit: Boolean = true
    val readAllData: LiveData<List<Memo>>
    private val repository: MemoRepository

    init {
        val memoDao = MemoDatabase.getDatabase(
            application
        ).memoDao()
        repository = MemoRepository(memoDao)
        readAllData = repository.readAllData
    }

    fun insertData(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertData(memo)
        }
    }

    fun updateData(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(memo)
        }
    }

    fun deleteData(memo: Memo) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteData(memo)
        }
    }

}

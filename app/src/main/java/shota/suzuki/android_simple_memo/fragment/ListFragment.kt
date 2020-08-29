package shota.suzuki.android_simple_memo.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import shota.suzuki.android_simple_memo.R
import shota.suzuki.android_simple_memo.adapter.ListAdapter
import shota.suzuki.android_simple_memo.data.Memo
import shota.suzuki.android_simple_memo.data.MemoViewModel
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.android.synthetic.main.fragment_list.view.*
import java.util.*

class ListFragment : Fragment(), ListAdapter.OnItemClickListener {

    private lateinit var mMemoViewModel: MemoViewModel
    private lateinit var adapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)

        adapter = ListAdapter(this)
        val recyclerView = view.memo_recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mMemoViewModel = ViewModelProvider(this).get(MemoViewModel::class.java)
        mMemoViewModel.readAllData.observe(viewLifecycleOwner, Observer { memo ->
            adapter.setData(memo)
        })

        view.save_or_update_btn.setOnClickListener(View.OnClickListener {
            val id      = content_id.text.toString()
            val content = content_body.text.toString()
            if (!TextUtils.isEmpty(content)) {
                if (mMemoViewModel.isEdit) {
                    val memo = Memo(0, content, Date())
                    mMemoViewModel.insertData(memo)
                    Toast.makeText(requireContext(), "メモを保存しました", Toast.LENGTH_SHORT).show()
                } else {
                    val memo = Memo(
                        Integer.parseInt(id),
                        content,
                        Date()
                    )
                    mMemoViewModel.updateData(memo)
                    Toast.makeText(requireContext(), "メモを更新しました", Toast.LENGTH_SHORT).show()
                }
                initData()
            } else {
                Toast.makeText(requireContext(), "メモを入力して下さい", Toast.LENGTH_SHORT).show()
            }

        })

        view.cancel_or_delete_btn.setOnClickListener(View.OnClickListener {
            if (!mMemoViewModel.isEdit) {
                val memo = Memo(
                    Integer.parseInt(content_id.text.toString()),
                    content_body.text.toString(),
                    Date()
                )
                mMemoViewModel.deleteData(memo)
                Toast.makeText(requireContext(), "メモを削除しました", Toast.LENGTH_SHORT).show()
            }
            initData()
            cancel_or_delete_btn.text = "キャンセル"
            mMemoViewModel.isEdit = true
        })

        setHasOptionsMenu(true)
        return view
    }

    override fun onItemClick(position: Int) {
        val memo = adapter.getData(position)
        cancel_or_delete_btn.text = "削除"
        content_id.text = strToEditable(memo.id.toString())
        content_body.text = strToEditable(memo.content.toString())
        mMemoViewModel.isEdit = false
    }

    private fun initData() {
        content_id.text = null
        content_body.text = null
    }

    private fun strToEditable(value: String?): Editable {
        return Editable.Factory.getInstance().newEditable(value)
    }



}

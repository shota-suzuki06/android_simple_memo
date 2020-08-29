package shota.suzuki.android_simple_memo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import shota.suzuki.android_simple_memo.R
import shota.suzuki.android_simple_memo.common.DataParseObject
import shota.suzuki.android_simple_memo.data.Memo
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter(private val listener: OnItemClickListener): RecyclerView.Adapter<ListAdapter.MemoViewHolder>() {

    private var memoList = emptyList<Memo>()

    inner class MemoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {


        init {
            itemView.list_item_layout.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }


    }

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        return MemoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount(): Int {
        return memoList.size
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val currentItem = memoList[position]
        holder.itemView.content_et.text = currentItem.content.toString()
        holder.itemView.date_et.text    = "登録日時: " + DataParseObject.dateToStringParse(currentItem.date)
    }

    fun setData(memo: List<Memo>) {
        this.memoList = memo
        notifyDataSetChanged()
    }

    fun getData(position: Int): Memo {
        return this.memoList[position]
    }

}

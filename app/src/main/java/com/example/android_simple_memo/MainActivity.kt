package com.example.android_simple_memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.android_simple_memo.adapter.ListAdapter
import com.example.android_simple_memo.data.MemoViewModel
import kotlinx.android.synthetic.main.list_item.view.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


}

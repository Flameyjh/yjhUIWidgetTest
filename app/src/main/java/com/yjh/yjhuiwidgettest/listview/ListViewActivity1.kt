package com.yjh.yjhuiwidgettest.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.yjh.yjhuiwidgettest.R

/*
* 3. ListView
* 最简单的用法，不额外写Adapter，item的布局，以及数据类
* */
class ListViewActivity1 : AppCompatActivity() {

    private val data = listOf("Apple", "Banana", "Orange", "Apple", "Banana", "Orange",
        "Apple", "Banana", "Orange", "Apple", "Banana", "Orange", "Apple", "Banana", "Orange"
        , "Apple", "Banana", "Orange", "Apple", "Banana", "Orange")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        findViewById<ListView>(R.id.listView).adapter = adapter
    }
}

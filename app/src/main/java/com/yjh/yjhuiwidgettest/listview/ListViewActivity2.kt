package com.yjh.yjhuiwidgettest.listview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import android.widget.Toast
import com.yjh.yjhuiwidgettest.R

/*
* 3. ListView
* 自定义的用法，额外写Adapter，item的布局，数据类，以及使用ViewHolder优化运行效率。
* */
class ListViewActivity2 : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_view2)

        initFruits() //初始化水果数据
        val listView = findViewById<ListView>(R.id.listView)
        listView.adapter = FruitAdapter(this, R.layout.fruit_item, fruitList)
        listView.setOnItemClickListener{ _, _, position, _ ->
            val fruit = fruitList[position]
            Toast.makeText(this, fruit.name, Toast.LENGTH_SHORT).show()
        }
    }

    private fun initFruits() {
        repeat(10){
            fruitList.add(Fruit("Apple", R.drawable.ic_launcher_foreground))
            fruitList.add(Fruit("Banana", R.drawable.ic_launcher_foreground))
            fruitList.add(Fruit("Orange", R.drawable.ic_launcher_foreground))
        }
    }
}
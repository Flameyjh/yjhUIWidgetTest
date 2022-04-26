package com.yjh.yjhuiwidgettest.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yjh.yjhuiwidgettest.R
import com.yjh.yjhuiwidgettest.listview.Fruit

class RecyclerViewActivity : AppCompatActivity() {

    private val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycle_view)

        initFruits() //初始化水果数据
        val recyclerView: RecyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this) //LayoutManager用于指定RecycleView的布局方式
        recyclerView.adapter = FruitAdapter(fruitList)
    }

    private fun initFruits() {
        repeat(10){
            fruitList.add(Fruit("Apple", R.drawable.ic_launcher_foreground))
            fruitList.add(Fruit("Banana", R.drawable.ic_launcher_foreground))
            fruitList.add(Fruit("Orange", R.drawable.ic_launcher_foreground))
        }
    }
}
package com.yjh.yjhuiwidgettest

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast

/*
* 2. 引入布局和创建自定义控件,重点是可以加入点击事件。
* */
class TitleLayout(context: Context, attrs: AttributeSet): LinearLayout(context, attrs) {

    init {
        //动态加载标题栏
        LayoutInflater.from(context).inflate(R.layout.title, this) //inflate的第一个参数是要加载的布局文件的id，第二个参数是添加一个父布局

        findViewById<Button>(R.id.titleBackBtn).setOnClickListener {
            val activity = context as Activity
            activity.finish()
        }
        findViewById<Button>(R.id.titleEditBtn).setOnClickListener {
            Toast.makeText(context, "You clicked Edit Button", Toast.LENGTH_SHORT).show()
        }
    }
}
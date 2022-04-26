package com.yjh.yjhuiwidgettest.fruitdetail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.yjh.yjhuiwidgettest.R

/*
* 9. Material Design——可折叠式标题栏：CollapsingToolbarLayout(ImageView图片和固定的Toolbar切换).
*                                  NestedScrollView(在ScrollView的基础上增加了嵌套响应滚动事件).
*                                  fitsSystemWindows(此属性充分利用系统状态栏空间).
* */
class FruitActivity : AppCompatActivity() {
    companion object{
        const val FRUIT_NAME = "fruit_name"
        const val FRUIT_IMAGE_ID = "fruit_image_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fruit)

        val fruitName = intent.getStringExtra(FRUIT_NAME)?:""
        val fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        findViewById<CollapsingToolbarLayout>(R.id.collapsingToolbar).title = fruitName
        Glide.with(this).load(fruitImageId).into(findViewById(R.id.fruitImageView))
        findViewById<TextView>(R.id.fruitContentText).text = generateFruitContent(fruitName)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun generateFruitContent(fruitName: String): CharSequence = fruitName.repeat(500)
}
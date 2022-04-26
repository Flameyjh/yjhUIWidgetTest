package com.yjh.yjhuiwidgettest.materialcardview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.yjh.yjhuiwidgettest.R
import com.yjh.yjhuiwidgettest.fruitdetail.FruitActivity
import com.yjh.yjhuiwidgettest.listview.Fruit

class FruitAdapter(val context: Context, val fruitList: List<Fruit>) : RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fruitImage: ImageView = itemView.findViewById(R.id.fruitImage)
        val fruitName: TextView = itemView.findViewById(R.id.fruitName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FruitAdapter.ViewHolder {
        //卡片式布局: R.layout.card_item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        val holder = ViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            //跳到详情页
            val intent = Intent(context, FruitActivity::class.java).apply {
                putExtra(FruitActivity.FRUIT_NAME, fruit.name)
                putExtra(FruitActivity.FRUIT_IMAGE_ID, fruit.imageId)
            }
            context.startActivity(intent)
            Toast.makeText(parent.context, "you clicked itemView ${fruit.name}", Toast.LENGTH_SHORT).show()
        }
        holder.fruitName.setOnClickListener {
            val position = holder.adapterPosition
            Toast.makeText(parent.context, "you clicked textView ${fruitList[position].name}", Toast.LENGTH_SHORT).show()
        }
        return holder
    }

    override fun onBindViewHolder(holder: FruitAdapter.ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.fruitName.text = fruit.name
        Glide.with(context).load(fruit.imageId).into(holder.fruitImage) //Glide加载图片，具有图像压缩功能
    }

    override fun getItemCount(): Int = fruitList.size
}
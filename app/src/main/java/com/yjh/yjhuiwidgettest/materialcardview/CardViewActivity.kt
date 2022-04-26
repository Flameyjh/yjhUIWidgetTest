package com.yjh.yjhuiwidgettest.materialcardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.yjh.yjhuiwidgettest.R
import com.yjh.yjhuiwidgettest.listview.Fruit
import kotlin.concurrent.thread

/*
* 5. Material Design——滑动菜单：ToolBar(上方标题栏). DrawerLayout(滑动菜单). NavigationView(导航视图).
* 6. Material Design——悬浮按钮和可交互提示：FloatingActionButton(悬浮按钮). Snackbar(可交互提示). CoordinatorLayout(协调布局).
* 7. Material Design——卡片式布局: MaterialCardView(RecycleView的item子布局使用).
*                               AppBarLayout(解决RecycleView把Toolbar遮挡的问题，实际是一个垂直方向的LinearLayout).
* 8. Material Design——下拉刷新：SwipeRefreshLayout.
* */
class CardViewActivity : AppCompatActivity() {

    lateinit var drawerLayout: DrawerLayout
    lateinit var swipeRefresh: SwipeRefreshLayout

    val fruits = mutableListOf( //listof不可变，mutableListOf可变
        Fruit("Apple", R.drawable.apple), Fruit("Banana", R.drawable.banana),
        Fruit("Watermelon", R.drawable.watermelon)
    )
    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_view)

        //Toolbar
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true) //显示该actionBar的导航按钮（即左上角）
            it.setHomeAsUpIndicator(R.drawable.ic_menu) //设置导航按钮的图标
        }

        //NavigationView
        drawerLayout = findViewById(R.id.drawerLayout)
        findViewById<NavigationView>(R.id.navView).apply {
            setCheckedItem(R.id.navCall) //设置默认选中为Call菜单项
            setNavigationItemSelectedListener {
                drawerLayout.closeDrawers()
                true
            }
        }

        //FloatingActionButton. Snackbar. CoordinatorLayout.
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        //卡片式布局
        initFruits() //初始化水果数据
        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        val adapter = FruitAdapter(this, fruitList)
        recyclerView.layoutManager = GridLayoutManager(this, 2) //网格LayoutManager,每列2个
        recyclerView.adapter = adapter

        //下拉刷新
        swipeRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeRefresh).apply {
            setColorSchemeResources(R.color.yellow)
            setOnRefreshListener {
                refreshFruits(adapter)
            }
        }
    }

    private fun refreshFruits(adapter: FruitAdapter) {
        thread {
            //具体刷新逻辑
            Thread.sleep(2000)
            runOnUiThread{
                initFruits()
                adapter.notifyDataSetChanged()
                swipeRefresh.isRefreshing = false
            }
        }
    }

    //Toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.backup -> Toast.makeText(this, "you clicked Backup", Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "you clicked Delete", Toast.LENGTH_SHORT).show()
            R.id.setting -> Toast.makeText(this, "you clicked Setting", Toast.LENGTH_SHORT).show()

            //DrawerLayout
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    //每次打开程序看到的水果数据不同
    private fun initFruits() {
        fruitList.clear()
        repeat(50){
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }
}
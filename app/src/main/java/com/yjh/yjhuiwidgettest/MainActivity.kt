package com.yjh.yjhuiwidgettest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import com.google.android.material.snackbar.Snackbar
import com.yjh.yjhuiwidgettest.databinding.ActivityMainBinding
import com.yjh.yjhuiwidgettest.listview.ListViewActivity1
import com.yjh.yjhuiwidgettest.listview.ListViewActivity2
import com.yjh.yjhuiwidgettest.materialcardview.CardViewActivity
import com.yjh.yjhuiwidgettest.recyclerview.RecyclerViewActivity

/*
* 常用UI的使用
* 1. 常用控件的使用方法：TextView. Button. EditText. ImageView. ProgressBar(进度条). AlertDialog(对话框)。
* 2. 引入布局和创建自定义控件(查看activity_main.xml)。
* 3. ListView
* 4. RecycleView
* 5. Material Design——滑动菜单：ToolBar(上方标题栏). DrawerLayout(滑动菜单). NavigationView(导航视图).
* 6. Material Design——悬浮按钮和可交互提示：FloatingActionButton(悬浮按钮). Snackbar(可交互提示). CoordinatorLayout(协调布局).
* 7. Material Design——卡片式布局: MaterialCardView(RecycleView的item子布局使用).
*                               AppBarLayout(解决RecycleView把Toolbar遮挡的问题，实际是一个垂直方向的LinearLayout).
* 8. Material Design——下拉刷新：SwipeRefreshLayout.
* 9. Material Design——可折叠式标题栏：CollapsingToolbarLayout(ImageView图片和固定的Toolbar切换).
*                                  NestedScrollView(在ScrollView的基础上增加了嵌套响应滚动事件).
*                                  fitsSystemWindows(此属性充分利用系统状态栏空间).
* */
class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //EditText. ImageView. ProgressBar. AlertDialog
        binding.button.setOnClickListener(this)

        //ListView
        binding.listViewBtn1.setOnClickListener {
            startActivity(Intent(this, ListViewActivity1::class.java))
        }
        binding.listViewBtn2.setOnClickListener {
            startActivity(Intent(this, ListViewActivity2::class.java))
        }

        //RecycleView
        binding.recycleView.setOnClickListener {
            startActivity(Intent(this, RecyclerViewActivity::class.java))
        }

        //Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true) //显示该actionBar的导航按钮（即左上角）
            it.setHomeAsUpIndicator(R.drawable.ic_menu) //设置导航按钮的图标
        }

        //NavigationView
        binding.navView.setCheckedItem(R.id.navCall) //设置默认选中为Call菜单项
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()
            true
        }

        //FloatingActionButton. Snackbar. CoordinatorLayout.
        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Data deleted", Snackbar.LENGTH_SHORT)
                .setAction("Undo"){
                    Toast.makeText(this, "Data restored", Toast.LENGTH_SHORT).show()
                }
                .show()
        }

        //MaterialCardView
        binding.materialCardView.setOnClickListener {
            startActivity(Intent(this, CardViewActivity::class.java))
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
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> { //在此处添加逻辑
                //EditText
                val inputText = binding.editText.text.toString()
                Toast.makeText(this, inputText, Toast.LENGTH_SHORT).show()

                //ImageView
                binding.imageView.setImageResource(R.drawable.ic_launcher_background)

                //ProgressBar
//                if(binding.progressBar.visibility == View.VISIBLE){
//                    binding.progressBar.visibility = View.GONE
//                }else{
//                    binding.progressBar.visibility = View.VISIBLE
//                }
                binding.progressBar.progress = binding.progressBar.progress + 10 //进度条

                //AlertDialog
                AlertDialog.Builder(this).apply {
                    setTitle("This is a Dialog")
                    setMessage("Something important.")
                    setCancelable(false)
                    setPositiveButton("OK"){ dialog, which ->
                    }
                    setNegativeButton("Cancel"){ dialog, which ->
                    }
                    show()
                }

            }
        }
    }


}
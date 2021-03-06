package com.knilim.knilim.ui.main

import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.knilim.base.Utils
import com.knilim.knilim.R
import com.knilim.knilim.data.database.ImDatabase
import com.knilim.knilim.data.main.DialogManager
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 开启session-server
        mainViewModel.connectSessionServer()

        // 初始化数据库
        Utils.db = Room.databaseBuilder(applicationContext, ImDatabase::class.java, getString(R.string.database)).build()

        // 头部toolbar的处理
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = ""
        }

        // 底部导航栏
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        // viewModel的数据绑定工作
        bindData()

        // 一些关于UI层面的初始化工作
        initUI()
    }

    private fun bindData() {
        mainViewModel.user.observe(this, Observer {
            // 设置toolbar上的头像
            Glide.with(this)
                .load(it.avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(avatar)
            // 设置抽屉里的头像
            Glide.with(this)
                .load(it.avatar)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(draw_avatar)
            // 设置头像的旁边的文字
            draw_my_nickname.text = it.nickname
            draw_my_signature.text = it.signature
        })

        DialogManager.connectResponse.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }

    private fun initUI() {
        // 头像处的ImageView点击后, 弹出抽屉
        avatar.setOnClickListener {
            draw_container.openDrawer(GravityCompat.START)
        }
        // 抽屉的背景图
        Glide.with(this)
            .load("http://cdn.loheagn.com/2020-06-02-wallhaven-2evkjm.jpg")
            .into(draw_image_background)
    }
}

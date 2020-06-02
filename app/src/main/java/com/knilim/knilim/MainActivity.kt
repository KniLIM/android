package com.knilim.knilim

import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 头部toolbar的处理
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.title = ""
        }
        // 设置头像
        val avatarUrl = "http://cdn.loheagn.com/2020-06-02-12CEBEBD-0E57-41A9-91E9-2ADA8AC0B3AF.jpg"
        Glide.with(this)
            .load(avatarUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(avator)


        // 底部导航栏
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)

        // 执行一些初始化工作
        initActions()
    }

    private fun initActions() {
        // 头像处的ImageView点击后, 弹出抽屉
        avator.setOnClickListener {
            draw_container.openDrawer(GravityCompat.START)
        }
    }
}

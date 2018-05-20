package com.mvvmdemo.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.example.nguyentamthi.mvvmdemo.R
import com.mvvmdemo.mangalist.MangaListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                val fragment = MangaListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                val fragment = MangaListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                val fragment = MangaListFragment()
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }
}

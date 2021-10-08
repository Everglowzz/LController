package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lcontroller.LcUtils

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LcUtils.init(this,"XV1EKekyVJJEfln5vNBGk2rQ-MdYXbMMI","flgB6c1qplRYDSzT1pPixl3b","足球金手指")
    }
}
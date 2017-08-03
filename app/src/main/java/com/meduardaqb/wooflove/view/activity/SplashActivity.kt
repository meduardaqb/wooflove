package com.meduardaqb.wooflove.view.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.meduardaqb.wooflove.R
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val handler = Handler()

        val runnable = object : Runnable {
            override fun run() { startActivity<LoginActivity>() }
        }

        handler.postDelayed(runnable, 3000)
    }
}

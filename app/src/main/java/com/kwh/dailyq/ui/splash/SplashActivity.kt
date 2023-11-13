package com.kwh.dailyq.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.kwh.dailyq.AuthManager
import com.kwh.dailyq.R
import com.kwh.dailyq.ui.base.BaseActivity
import com.kwh.dailyq.ui.login.LoginActivity
import com.kwh.dailyq.ui.main.MainActivity
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        lifecycleScope.launch {
            delay(1000)

            if (AuthManager.accessToken.isNullOrEmpty()){
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
            } else {
                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            }

//            if (AuthManager.accessToken.isNullOrEmpty()) {
//                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
//            } else {
//                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
//            }

            finish()
        }

    }
}
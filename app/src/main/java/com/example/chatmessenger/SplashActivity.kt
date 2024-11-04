package com.example.chatmessenger

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.chatmessenger.activities.SignInActivity
import com.google.firebase.auth.FirebaseAuth

class SplashActivity : AppCompatActivity() {

    private lateinit var fbauth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.splash_layout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        fbauth = FirebaseAuth.getInstance()

        // تاخیر ۵ ثانیه‌ای قبل از هدایت
        Handler(Looper.getMainLooper()).postDelayed({
            if (fbauth.currentUser != null) {
                // اگر کاربر قبلاً وارد شده است، به MainActivity بروید
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                // در غیر این صورت، به صفحه ورود بروید
                startActivity(Intent(this, SignInActivity::class.java))
            }
            finish() // برای جلوگیری از بازگشت به صفحه اسپلاش
        }, 5000) // ۵ ثانیه تاخیر

    }
}

package com.example.chaptereight

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SecActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sec)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edName = findViewById<EditText>(R.id.edName)
        val edPhone = findViewById<EditText>(R.id.edPhone)
        val btnSend = findViewById<Button>(R.id.btnSend)


        // 設定按鈕監聽器，取得輸入的姓名與電話
        btnSend.setOnClickListener {
            // 判斷是否輸入資料
            when {
                edName.text.isEmpty() -> showToast("請輸入內容")
                edPhone.text.isEmpty() -> showToast("請輸入日期")
                else -> {
                    val b = Bundle()
                    b.putString("name", edName.text.toString() )
                    b.putString("phone",edPhone.text.toString() )
                    // 使用setResult() 回傳聯絡人資料
                    setResult(Activity.RESULT_OK, Intent().putExtras(b) )
                    finish()
                }
            }
        }
    }

    // 建立showToast方法顯示Toast訊息
    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}
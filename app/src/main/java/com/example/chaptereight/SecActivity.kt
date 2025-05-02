package com.example.chaptereight

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar
import java.util.jar.Attributes.Name

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
        val btndate = findViewById<Button>(R.id.btnDate)
        btndate.setOnClickListener{

            val x = showDatePickerDialog(edPhone)
        }


        // 設定按鈕監聽器，取得輸入的姓名與電話
        btnSend.setOnClickListener {
            // 判斷是否輸入資料
            when {
                edName.text.isEmpty() -> showToast("請輸入內容")
                edPhone.text.isEmpty() -> showToast("請輸入日期")
                else -> {
                    val b = Bundle()
                    b.putString("note", edName.text.toString() )
                    b.putString("date",edPhone.text.toString() )
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
    private fun showDatePickerDialog(edPhone: EditText) {
        // 獲取當前日期
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // 創建並顯示 DatePickerDialog

        val datePickerDialog = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
            val selectedDate = "$selectedYear/${selectedMonth + 1}/$selectedDay"
            edPhone.setText(selectedDate) // 更新 EditText 顯示選擇的日期
        }, year, month, day)

        datePickerDialog.show()
    }
}
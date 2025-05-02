package com.example.chaptereight

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var myAdapter: MyAdapter
    //宣告contacts陣列，表示聯絡人資料
    private val contacts = ArrayList<Contact>()

    private val startForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result: ActivityResult ->
        if(result.resultCode == RESULT_OK){
            //取得回傳的Intent，並從Intent中取得聯絡人資訊
            val intent = result.data
            val name = intent?.getStringExtra("name") ?:""
            val phone = intent?.getStringExtra("phone") ?:""
            //新增聯絡人資料
            contacts.add(Contact(name,phone))
            //更新清單
            myAdapter.notifyDataSetChanged()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        //建立linearLayoutManager物件，設定垂直排列
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation= LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = linearLayoutManager
        //建立MyAdapter並連接recyclerView
        myAdapter = MyAdapter(contacts)
        recyclerView.adapter = myAdapter
        //設定按鈕監聽器，使用 startForResult前往SecActivity
        btnAdd.setOnClickListener{
            val i = Intent(this, SecActivity::class.java)
            startForResult.launch(i)
        }
    }
}
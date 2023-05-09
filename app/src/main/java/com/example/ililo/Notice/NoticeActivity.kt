package com.example.ililo.Notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ililo.databinding.ActivityNoticeBinding

class NoticeActivity : AppCompatActivity() {
    private val binding: ActivityNoticeBinding by lazy {
        ActivityNoticeBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //뒤로가기
        binding.btnBack.setOnClickListener {
            finish()
        }
    }
}
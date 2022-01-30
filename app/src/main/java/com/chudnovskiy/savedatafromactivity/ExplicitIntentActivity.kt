package com.chudnovskiy.savedatafromactivity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.chudnovskiy.savedatafromactivity.MainActivity.Companion.ANSWER

class ExplicitIntentActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)
        supportActionBar?.hide()

        val intent = Intent()
        intent.putExtra(ANSWER, "42")
        setResult(RESULT_OK, intent)
    }
}
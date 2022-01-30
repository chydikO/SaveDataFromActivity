package com.chudnovskiy.savedatafromactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    private var mEditText: EditText? = null
    val TAG = MainActivity::class.java.simpleName
    val KEY = "massage_key"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        mEditText = findViewById(R.id.massage_edit_text)
        if (savedInstanceState != null) {
            val oldMessage = savedInstanceState.getString(KEY)
            if (!TextUtils.isEmpty(oldMessage)) {
                mEditText?.setText(oldMessage)
            }
            Log.d(TAG, "onCreate: ".plus(oldMessage))
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val savedMessage = mEditText?.text.toString()
        outState.putString(KEY, savedMessage)
        Log.d(TAG, "onSaveInstanceState: ".plus(savedMessage))
    }
}
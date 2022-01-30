package com.chudnovskiy.savedatafromactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast

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

    fun startIntent(view: View) {
        if (!TextUtils.isEmpty(mEditText?.text.toString())) {
            val textMessage = mEditText?.text.toString()
            val sendIntent = Intent()
            sendIntent.setAction(Intent.ACTION_SEND)
            sendIntent.putExtra(Intent.EXTRA_TEXT, textMessage)
            sendIntent.setType("text/plain")

            val title = "Send"
            val chooser: Intent = Intent.createChooser(sendIntent, title)
            if (sendIntent.resolveActivity(packageManager) != null) {
                startActivity(chooser)
            }
        } else {
            Toast.makeText(this, "Empty text !!!", Toast.LENGTH_LONG).show()
        }
    }
}
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

    companion object {
        const val OUR_REQUEST_CODE = 42
        const val KEY = "massage_key"
        const val ANSWER = "answer"
    }

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

    fun startExplicitIntent(view: View) {
        val intent = Intent(this,ExplicitIntentActivity::class.java)
        startActivityForResult(intent, OUR_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == OUR_REQUEST_CODE) {
            var answer: String = ""
            if (resultCode == RESULT_OK && data != null) {
                answer = data?.getStringExtra(ANSWER).toString()
                mEditText?.setText(answer)
            }
            Log.d(TAG, "onActivityResult: ".plus(answer))
        }
    }
}
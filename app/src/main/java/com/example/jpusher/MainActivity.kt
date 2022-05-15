package com.example.jpusher

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Log
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tokenTextView.setText("Trying to get token...")

        var tokenTask = getToken()
            .addOnSuccessListener(){
                tokenTextView.setText("$it")
            }
            .addOnFailureListener(){
                tokenTextView.setText("Failed to get token.")
            }

        shareTokenButton.setOnClickListener {
            val sharedValue = tokenTextView.text.toString()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.type="text/plain"
            shareIntent.putExtra(Intent.EXTRA_TEXT, sharedValue);
            startActivity(Intent.createChooser(shareIntent,"Share via"))
        }

    }


    private fun getToken(): Task<String> {
        var token = FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                Log.w(ContentValues.TAG, "Fetching FCM registration token failed", task.exception)
                return@OnCompleteListener
            }
            //val msg = getString(R.string.msg_token_fmt, task.result)
            //Token value c5-O7IyJQqy4A21AjcZn_W:APA91bEIubgRuvPtVTltJYg9-n3LI53XymgqR9vqlBW0Oelbq2fOCnz3b_Z_kwlh7RXbIxBEf0jVaPM5O5eZ37bEd5-yjMmr8g4cqE9BrGqS-u6kLKNv62-kr50lsZRJ84__EjL1xAku
            //Log.d(TAG, this.token + "1")
            //Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show()
        })
        return token
    }

}
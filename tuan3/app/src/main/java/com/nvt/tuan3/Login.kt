package com.nvt.tuan3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        val acProfile:Intent = Intent(this,Profile::class.java)
        val passwordText:EditText = findViewById(R.id.edittextpassword)
        val userText:EditText = findViewById(R.id.edittextuser)
        val btnLogin:Button = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                val password:String = passwordText.text.toString();
                val user:String = userText.text.toString();
                if(user.equals("ronaldo@gmail.com", ignoreCase = true)) {
                    if(password.equals("123456")) {
                        acProfile.putExtra("email",user);
                        startActivity(acProfile);
                    }
                    else showMessage("Invalid password")
                }
                else showMessage("Invalid username")
            }
        })
    }
    private fun showMessage(message :String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}
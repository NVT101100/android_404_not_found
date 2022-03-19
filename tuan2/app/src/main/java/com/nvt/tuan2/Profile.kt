package com.nvt.tuan2

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

class Profile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val email : String = intent.getStringExtra("email").toString()
        val emailView : TextView = findViewById(R.id.email);
        val nameView : TextView = findViewById(R.id.username)
        val phoneView : TextView = findViewById(R.id.phone)
        val builder1 = AlertDialog.Builder(this)
        val builder2 = AlertDialog.Builder(this)


        emailView.setText(email)

        findViewById<TextInputLayout>(R.id.nameEdit).setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                showDialog(nameView)
            }
        })

        findViewById<TextInputLayout>(R.id.emailEdit).setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                showDialog(emailView)
            }
        })

        findViewById<TextInputLayout>(R.id.phoneEdit).setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                showDialog(phoneView)
            }
        })
    }
    private fun showDialog(edit : TextView){
        val builder = AlertDialog.Builder(this)
        val inflater : LayoutInflater = layoutInflater
        val dialogLayout : View = inflater.inflate(R.layout.edit_text_layout,null,true)
        val edittext : EditText = dialogLayout.findViewById(R.id.edittextdialog)
        builder.setView(dialogLayout);
        with(builder){
            setTitle("Edit")
            setPositiveButton("OK"){dialog,which ->
                edit.text = edittext.text.toString().trim()
                edittext.text = null
            }
            setNegativeButton("Cancel"){dialog,which ->
                dialog.cancel()
            }
            show()
        }
    }
}
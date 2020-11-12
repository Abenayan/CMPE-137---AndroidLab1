package com.example.androidlab1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList
import android.os.Handler
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod


class MainActivity : AppCompatActivity(){
    var incorrect: Int = 0
    var listOfID = ArrayList<Int>()
    val onetimepasseword = "CMPE#137"
    var passwordShow = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun login(v: View?){
        password.setTransformationMethod(null)
        message.text = ""
        var username = Integer.parseInt(username.text.toString())

        if (incorrect == 2){
            message?.text = "Wrong password: Wait 30 seconds"
            password.isEnabled = false
            Handler().postDelayed(Runnable { password.setEnabled(true) }, 30000)
            incorrect = 0
        }

        if (listOfID.contains(username)) {
            message?.text = "Inside"
            print("Its here")
            if (password.text.toString().equals(onetimepasseword)) {
                message?.text = "Success"
            } else if (password.text.toString().toUpperCase().equals(onetimepasseword)) {
                incorrect += 1
                message.text = "Try uppercase on the password"
            } else {
                incorrect += 1
                print("It should come here")
                message.text = "Invalid password"
            }
        }
        else{
            message?.text = "Invalid username"
        }

    }

    fun register(v: View?){
        message.text = "Adding user"
        var user = Integer.parseInt(username.getText().toString())
        if(username.text.isNotEmpty() && username.text.length == 9 && !listOfID.contains(user)) {
            listOfID.add(user)
        }
        else if(listOfID.contains(user)){
            message?.text = "User is registered"
        }
        else{
            if(message != null){
                message?.setText("Username must be 9 numbers")
            }
        }
    }

    fun showPassword(v: View){
        if(passwordShow == 0){
            password.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordShow = 1
        } else{
            password.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordShow = 0
        }
    }

}

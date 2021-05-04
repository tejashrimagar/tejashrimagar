package com.example.canwilllogin.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.canwilllogin.R
import com.example.canwilllogin.RoomDatabase.LoginTable
import com.example.canwilllogin.ViewModel.LoginViewModel
import com.example.canwilllogin.databinding.ActivityMainBinding
import com.example.canwilllogin.model.LoginRequest
import com.example.canwilllogin.model.LoginResponse
import com.example.canwilllogin.remote.RetrofitAPIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private var binding: ActivityMainBinding? = null
    private var etUserName: EditText? = null
    private var etPassword: EditText? = null
    private var btLogin: Button? = null
    private var loginViewModel: LoginViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this@LoginActivity, R.layout.activity_main)

        etUserName = findViewById<EditText>(R.id.et_username)
        etPassword = findViewById<EditText>(R.id.et_passowrd)
        btLogin = findViewById<Button>(R.id.bt_login)

        btLogin!!.setOnClickListener(this)

        loginViewModel = ViewModelProviders.of(this@LoginActivity).get(LoginViewModel::class.java)

        loginViewModel?.getGetAllData()!!.observe(this@LoginActivity, Observer<List<LoginTable?>?> {
            fun onChanged(@Nullable data: List<LoginTable>) {
                try {
                    /*binding?.etUsername?.text = Objects.requireNonNull(data.get(0).userName)
                    binding?.etPassowrd?.text = Objects.requireNonNull(data.get(0).password))*/

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        })
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.bt_login -> {
                if (areFieldsValid()) {
                    proceedToLogin(etUserName!!.text.toString(), etPassword!!.text.toString())
                }
            }
        }
    }

    private fun proceedToLogin(username: String, password: String) {

        var loginRequest = LoginRequest(username, password)
        var loginResponseCall = RetrofitAPIClient.getUserServcie()
            .userLogin("application/json",loginRequest)
        loginResponseCall!!.enqueue(object : Callback<LoginResponse?> {
            override fun onResponse(
                call: Call<LoginResponse?>,
                response: Response<LoginResponse?>
            ) {
                if (response.isSuccessful) {
                    val loginTable = LoginTable()
                    loginTable.userName = etUserName!!.text.toString()
                    loginTable.password = etPassword!!.text.toString()
                    loginViewModel?.insert(loginTable);
                    var intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    startActivity(intent)
                }
            }

            override fun onFailure(
                call: Call<LoginResponse?>,
                t: Throwable
            ) {
                Toast.makeText(this@LoginActivity, "Failure...", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun areFieldsValid(): Boolean {
        if (etUserName!!.text.toString().isEmpty()) {
            etUserName!!.setError(R.string.username_empty.toString())
            return false
        } else if (etPassword!!.text.toString().isEmpty()) {
            etPassword!!.setError(R.string.password_empty.toString())
            return false
        } else {
            return true
        }
    }
}

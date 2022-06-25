package com.example.mvvmdemo.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import com.example.mvvmdemo.HomeActivity
import com.example.mvvmdemo.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        with(binding) {
            etUsername.apply {
                addTextChangedListener { viewModel.onUsernameChanged(binding.etUsername.id, it) }
            }
            etPassword.apply {
                addTextChangedListener { viewModel.onPasswordChanged(binding.etPassword.id, it) }
            }

            with(viewModel) {
                btnLogin.setOnClickListener {
                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                    intent.putExtra("loginModel", viewModel.onLoginClicked())
                    startActivity(intent)
                    finish()
                }
                usernameError.observe(this@LoginActivity) {
                    tilUsername.error = it?.let { errorId -> getString(errorId) }
                }
                passwordError.observe(this@LoginActivity) {
                    tilPassword.error = it?.let { errorId -> getString(errorId) }
                }
                isLoginButtonEnabled.observe(this@LoginActivity) { btnLogin.isEnabled = it }
            }
        }
    }
}
package com.example.motivation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.motivation.R
import com.example.motivation.databinding.ActivityUserNameBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

class UserNameActivity : AppCompatActivity() {

    private val binding by lazy { ActivityUserNameBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.btnSave.setOnClickListener {
            handleSave()
        }
        verifyUserName()
    }
    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if (name.isNotEmpty()) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
    private fun handleSave() {
        val name = binding.editName.text.toString()
        if (name.isNotEmpty()) {
            SecurityPreferences(this).storeString("USER_NAME", name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        } else {
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}
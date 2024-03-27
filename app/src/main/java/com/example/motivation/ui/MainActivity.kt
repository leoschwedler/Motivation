package com.example.motivation.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.motivation.R
import com.example.motivation.data.Mock
import com.example.motivation.databinding.ActivityMainBinding
import com.example.motivation.infra.MotivationConstants
import com.example.motivation.infra.SecurityPreferences

class MainActivity : AppCompatActivity() {

    private val mock = Mock()
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var categoryId = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        handleUserName()
        handleFilter(R.id.imgAll)
        with(binding) {
            btnNewFhrase.setOnClickListener {
                val phrase = mock.getPhrase(categoryId)
                txtPhrase.text = phrase.description
                Log.i("Phrase", categoryId.toString())
            }
            imgAll.setOnClickListener {
                handleFilter(it.id)
            }
            imgHappy.setOnClickListener {
                handleFilter(it.id)
            }
            imgSunny.setOnClickListener {
                handleFilter(it.id)
            }
        }
    }
    private fun handleFilter(id: Int) {
        binding.imgAll.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))
        binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.dark_purple))

        if (id == R.id.imgAll) {
            binding.imgAll.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.ALL
        } else if (id == R.id.imgHappy) {
            binding.imgHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.HAPPY
        } else if (id == R.id.imgSunny) {
            binding.imgSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
            categoryId = MotivationConstants.FILTER.SUNNY
        }
    }
    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.txtUserName.text = "Olá, $name!"
    }
}
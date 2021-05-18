package com.example.comcasttest

import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comcasttest.databinding.ActivityMainBinding
import com.example.comcasttest.view.FlavorFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        if(savedInstanceState == null)
            supportFragmentManager.beginTransaction().replace(
                R.id.container, FlavorFragment())
                .commit()
    }

    private fun initViews() {
        setSupportActionBar(binding.mainToolbar)
        when(BuildConfig.FLAVOR){
            "simpsons" -> {
                binding.mainToolbar.title = getString(R.string.app_name)
                binding.mainToolbar.background = ColorDrawable( resources.getColor(R.color.bkg_simpson))
            }
            "wire" -> {
                binding.mainToolbar.title = getString(R.string.app_name)
                binding.mainToolbar.background = ColorDrawable( resources.getColor(R.color.bkg_wire))
            }
        }
    }
}
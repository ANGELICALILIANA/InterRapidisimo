package com.example.interrapidisimo.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.interrapidisimo.R
import com.example.interrapidisimo.databinding.ActivityMainBinding
import com.example.interrapidisimo.fragment.CreateEntityFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(1000)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        replaceFragment(CreateEntityFragment.newInstance())

    }

    /**
     * Reemplaza el fragmento necesario
     */
    private fun replaceFragment(fragment: Fragment) {
        binding.fragment.visibility = View.VISIBLE
        val fragmentTransaction = this.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment, fragment)
        fragmentTransaction.commit()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        this.startActivity(intent)
    }

}
package com.example.androidcomponents

import android.Manifest
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.services.ForegroundService

class MainActivity : AppCompatActivity() {
    private val prefs by lazy {
        getSharedPreferences("savedId", Context.MODE_PRIVATE)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                0
            )
        }
        val savedId = scanSharedPreference()
        if (intent.getBooleanExtra("state", false) && savedId.isNotEmpty()) {

            supportFragmentManager.commit {
                val item = PlaceholderContent.ITEMS[savedId.toInt() - 1]
                replace(
                    R.id.fragment,
                    InfoElementFragment.newInstance(item.id, item.content, item.details)
                )
                setReorderingAllowed(true)
            }
        } else {
            startService()
            supportFragmentManager.commit {
                replace<ElementListFragment>(R.id.fragment)
                setReorderingAllowed(true)
            }
        }


    }

    private fun startService() {
        Intent(this, ForegroundService::class.java).also {
            it.action = ForegroundService.Actions.START.toString()
            startService(it)
        }
    }

    private fun scanSharedPreference(): String {
        return prefs.getString("id", null) ?: ""
    }
}
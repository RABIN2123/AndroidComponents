package com.example.androidcomponents

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import com.example.androidcomponents.interfaces.MainViewModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.services.ForegroundService

class MainActivity : AppCompatActivity() {

    private val sharedViewModel: MainViewModel by viewModels {
        SharedViewModel.provideFactory(PlaceholderContent, applicationContext)
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
        sharedViewModel.selectFragment(intent.getBooleanExtra("state", false))
        when (sharedViewModel.listState.value.stateOfScreen) {
            ListState.Status.MAIN -> startElementListFragment()
            ListState.Status.INFO -> startInfoElementFragment()
        }
    }


    private fun startInfoElementFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.fragment,
                InfoElementFragment()
            )
            setReorderingAllowed(true)
        }
    }

    private fun startElementListFragment() {
        startService()
        supportFragmentManager.commit {
            replace(R.id.fragment, ElementListFragment())
            setReorderingAllowed(true)
        }
    }

    private fun startService() {
        Intent(this, ForegroundService::class.java).also {
            it.action = ForegroundService.Actions.START.toString()
            startService(it)
        }
    }
}
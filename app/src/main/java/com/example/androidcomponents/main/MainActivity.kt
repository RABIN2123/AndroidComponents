package com.example.androidcomponents.main

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.androidcomponents.list.ElementListFragment
import com.example.androidcomponents.infoscreen.InfoElementFragment
import com.example.androidcomponents.R
import com.example.androidcomponents.services.ForegroundService
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModel.provideFactory(
            interactors = setOf(
                CheckNotificationStateInteractor()
            ),
             applicationContext
        )
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
        mainViewModel.openFragment(intent.getBooleanExtra("state", false))
        Log.d("TAG", "stateOfScreen = ${mainViewModel.listState.value.stateOfScreen}")
        mainViewModel.listState.onEach {values ->
            when (values.stateOfScreen) {
                MainState.Status.MAIN -> startElementListFragment()
                MainState.Status.INFO -> startInfoElementFragment()
            }
        }.launchIn(lifecycleScope)

    }


    private fun startInfoElementFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.fragment,
                InfoElementFragment(mainViewModel.listState.value.id)
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
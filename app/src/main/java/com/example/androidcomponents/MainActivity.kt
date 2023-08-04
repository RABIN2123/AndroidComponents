package com.example.androidcomponents

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.example.androidcomponents.models.MainActivityModel
import com.example.androidcomponents.placeholder.PlaceholderContent
import com.example.androidcomponents.presenters.MainActivityPresenter
import com.example.androidcomponents.presenters.interfaces.MainActivityView
import com.example.androidcomponents.services.ForegroundService

class MainActivity : AppCompatActivity(), MainActivityView {
    private val presenter by lazy {
        MainActivityPresenter(model)
    }
    private val model by lazy {
        MainActivityModel(applicationContext)
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
        presenter.attachView(this)
        presenter.viewIsReady()
    }

    override fun startInfoElementFragment(savedId: Int) {
        supportFragmentManager.commit {
            val item = PlaceholderContent.ITEMS[savedId - 1]
            replace(
                R.id.fragment,
                InfoElementFragment.newInstance(item.id)
            )
            setReorderingAllowed(true)
        }
    }

    override fun startElementListFragment() {
        startService()
        supportFragmentManager.commit {
            replace<ElementListFragment>(R.id.fragment)
            setReorderingAllowed(true)
        }
    }

    override fun getState(): Boolean {
        return intent.getBooleanExtra("state", false)
    }

    private fun startService() {
        Intent(this, ForegroundService::class.java).also {
            it.action = ForegroundService.Actions.START.toString()
            startService(it)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
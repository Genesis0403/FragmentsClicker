package com.epam.fragmentsclicker

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.epam.fragmentsclicker.fragments.ClickButtonFragment
import com.epam.fragmentsclicker.fragments.ClickTextFragment

class MainActivity : AppCompatActivity(), ClickButtonFragment.ActionListener {

    private var clicks = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        clicks = savedInstanceState?.getInt(CLICKS_AMOUNT) ?: 0
        resolveFragments()
    }

    private fun resolveFragments() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            updatePortrait(fragment = ClickButtonFragment())
        } else {
            updateLandscape()
        }
    }

    override fun onButtonClick() {
        ++clicks
        if (resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            val textFragment = ClickTextFragment()
            updatePortrait(backStack = true, pendingTransaction = true, fragment = textFragment)
            textFragment.updateTextView(clicks.toString())
        } else {
            updateLandscape()
        }
    }

    private fun updatePortrait(
        backStack: Boolean = false,
        pendingTransaction: Boolean = false,
        fragment: Fragment
    ) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainActivity, fragment)
            if (backStack) addToBackStack(null)
            commit()
        }
        if (pendingTransaction) supportFragmentManager.executePendingTransactions()
    }

    private fun updateLandscape() {
        val textFragment = supportFragmentManager.findFragmentById(R.id.textViewFragment) as ClickTextFragment
        textFragment.updateTextView(clicks.toString())
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putInt(CLICKS_AMOUNT, clicks)
    }

    private companion object {
        private const val CLICKS_AMOUNT = "amount"
    }
}




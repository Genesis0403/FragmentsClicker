package com.epam.fragmentsclicker

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.epam.fragmentsclicker.fragments.ClickButtonFragment
import com.epam.fragmentsclicker.fragments.ClickTextFragment

class MainActivity : AppCompatActivity(), ClickButtonFragment.ActionListener {

    private var clicks = 0
    private var prevPortraitFragment: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        savedInstanceState?.apply {
            clicks = getInt(CLICKS_AMOUNT)
            prevPortraitFragment = getString(PREV_FRAGMENT_TAG)
        }
        resolveFragments()
    }

    private fun resolveFragments() {
        if (resources.configuration.orientation == PORTRAIT) {
            if (prevPortraitFragment == null || prevPortraitFragment == ClickButtonFragment.TAG) {
                updatePortrait(fragment = ClickButtonFragment.newInstance())
            } else {
                updatePortrait(fragment = ClickTextFragment.newInstance(clicks))
            }
            println(prevPortraitFragment)
        } else {
            updateLandscape()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (prevPortraitFragment == ClickTextFragment.TAG) {
            prevPortraitFragment = ClickButtonFragment.TAG
        }
    }

    override fun onButtonClick() {
        ++clicks
        if (resources.configuration.orientation == PORTRAIT) {
            updatePortrait(backStack = true, fragment = ClickTextFragment.newInstance(clicks))
            prevPortraitFragment = ClickTextFragment.TAG
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
        outState?.putString(PREV_FRAGMENT_TAG, prevPortraitFragment)
    }

    private companion object {
        private const val CLICKS_AMOUNT = "clickAmount"
        private const val PREV_FRAGMENT_TAG = "PREV_FRAGMENT_TAG"
        private const val PORTRAIT = Configuration.ORIENTATION_PORTRAIT
    }
}

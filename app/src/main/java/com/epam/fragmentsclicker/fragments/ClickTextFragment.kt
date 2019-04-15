package com.epam.fragmentsclicker.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epam.fragmentsclicker.R
import kotlinx.android.synthetic.main.click_text_fragment.*

class ClickTextFragment : Fragment() {

    fun updateTextView(amount: String) {
        clickTextView.text = amount
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickTextView.text = savedInstanceState?.getString(CLICK_AMOUNT) ?: "0"
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("CLICKS AMOUNT", clickTextView?.text.toString())
        outState.putString(CLICK_AMOUNT, clickTextView?.text.toString())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.click_text_fragment, container, false)
    }

    override fun onDetach() {
        super.onDetach()
        Log.d("LIFECYCLE","$context DETACHED!!!!")
    }

    private companion object {
        private const val CLICK_AMOUNT = "clickAmount"
    }
}
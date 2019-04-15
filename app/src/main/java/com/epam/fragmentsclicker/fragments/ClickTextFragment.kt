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

    override fun onStart() {
        super.onStart()
        if (arguments != null) {
            updateTextView(arguments?.getInt(CLICK_AMOUNT).toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.click_text_fragment, container, false)
    }

    companion object {
        private const val CLICK_AMOUNT = "clickAmount"
        const val TAG = "ClickTextFragment"

        fun newInstance(count: Int) =
            ClickTextFragment().apply {
                arguments = Bundle().apply {
                    putInt(CLICK_AMOUNT, count)
                }
            }
    }
}
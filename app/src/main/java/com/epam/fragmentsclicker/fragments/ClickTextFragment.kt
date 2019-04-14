package com.epam.fragmentsclicker.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.epam.fragmentsclicker.R
import kotlinx.android.synthetic.main.click_text_fragment.*

class ClickTextFragment : Fragment() {

    fun updateTextView(amount: String) {
        clickTextView.text = amount
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.click_text_fragment, container, false)
    }
}
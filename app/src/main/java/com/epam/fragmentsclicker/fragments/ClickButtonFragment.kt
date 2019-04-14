package com.epam.fragmentsclicker.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.epam.fragmentsclicker.R
import kotlinx.android.synthetic.main.click_button_fragment.*

class ClickButtonFragment : Fragment() {

    interface ActionListener {
        fun onButtonClick()
    }

    private lateinit var listener: ActionListener

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            listener = context as ActionListener
        } catch (e: Exception) {
            Toast.makeText(context, getString(R.string.listener_load_error), Toast.LENGTH_LONG)
                 .show()
        }
    }

    override fun onStart() {
        super.onStart()
        clickButton.setOnClickListener {
            listener.onButtonClick()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.click_button_fragment, container, false)
    }
}